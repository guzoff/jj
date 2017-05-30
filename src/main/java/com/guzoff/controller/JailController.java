package com.guzoff.controller;

import java.util.List;
import java.util.Locale;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.guzoff.model.Convict;
import com.guzoff.service.ConvictService;

@Controller
@RequestMapping("/")
@ComponentScan("com.guzoff")
public class JailController {

    @Autowired
    ConvictService service;

    @Autowired
    MessageSource messageSource;

    /*
     * This method will list all existing convicts.
     */
    @RequestMapping(value = {"/", "/list"}, method = RequestMethod.GET)
    public String listConvicts(ModelMap model) {

        List<Convict> convicts = service.findAllConvicts();
        model.addAttribute("convicts", convicts);
        return "allconvicts";
    }

    /*
     * This method will provide the medium to add a new convict.
     */
    @RequestMapping(value = {"/new"}, method = RequestMethod.GET)
    public String newConvict(ModelMap model) {
        Convict convict = new Convict();
        model.addAttribute("convict", convict);
        model.addAttribute("edit", false);
        return "imprison";
    }

    /*
     * This method will be called on form submission, handling POST request for
     * saving a convict in the database. It also validates the user's input.
     */
    @RequestMapping(value = {"/new"}, method = RequestMethod.POST)
    public String saveConvict(@Valid Convict convict, BindingResult result,
            ModelMap model) {

        if (result.hasErrors()) {
            return "imprison";
        }

        // Nickname uniqueness validation
        if (!service.isConvictNickUnique(convict.getId(), convict.getNickname())) {
            FieldError nicknameError = new FieldError("convict", "nickname", 
                    messageSource.getMessage("non.unique.nickname", 
                            new String[]{convict.getNickname()}, Locale.getDefault()));
            result.addError(nicknameError);
            return "imprison";
        }

        service.saveConvict(convict);

        model.addAttribute("result", "Convict " + convict.getNickname() 
                            + " was successfully jailed.");
        return "allright";
    }

    /*
     * This method will provide the medium to update an existing convict.
     */
    @RequestMapping(value = {"/edit-{nickname}-convict"}, method = RequestMethod.GET)
    public String editConvict(@PathVariable String nickname, ModelMap model) {
        Convict convict = service.findConvictByNick(nickname);
        model.addAttribute("convict", convict);
        model.addAttribute("edit", true);
        return "imprison";
    }

    /*
     * This method will be called on form submission, handling POST request for
     * updating a convict in the database. It also validates the user's input.
     */
    @RequestMapping(value = {"/edit-{nickname}-convict"}, method = RequestMethod.POST)
    public String updateConvict(@Valid Convict convict, BindingResult result,
            ModelMap model, @PathVariable String nickname) {

        if (result.hasErrors()) {
            return "imprison";
        }

        // Nickname uniqueness validation
        if (!service.isConvictNickUnique(convict.getId(), convict.getNickname())) {
            FieldError nicknameError = new FieldError("convict", "nickname", 
                    messageSource.getMessage("non.unique.nickname", 
                            new String[]{convict.getNickname()}, Locale.getDefault()));
            result.addError(nicknameError);
            return "imprison";
        }

        service.updateConvict(convict);

        model.addAttribute("result", "Convict " + convict.getNickname() 
                            + " was successfully rejailed =)");
        return "allright";
    }

    /*
     * This method will delete a convict by the nickname.
     */
    @RequestMapping(value = {"/delete-{nickname}-convict"}, method = RequestMethod.GET)
    public String deleteConvict(@PathVariable String nickname) {
        service.deleteConvictByNick(nickname);
        return "redirect:/list";
    }

}
