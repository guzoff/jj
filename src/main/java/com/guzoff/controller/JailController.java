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
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import javax.sql.DataSource;

@Controller
@RequestMapping("/")
@ComponentScan("com.guzoff")
public class JailController {

    private static boolean firstTime = true;
    @Autowired
    ConvictService service;

    @Autowired
    MessageSource messageSource;

    @Autowired
    DataSource dataSource;
    
    /*
     *  Starting our application
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String initConvicts(ModelMap model) {
        if (firstTime) {
            try (Connection conn = dataSource.getConnection()) {
                Statement stmt = conn.createStatement();
                stmt.executeUpdate("DROP TABLE IF EXISTS `convict`");
                stmt.executeUpdate("CREATE TABLE `convict` (\n" +
                                "  `id` int(11) NOT NULL AUTO_INCREMENT,\n" +
                                "  `nickname` varchar(50) COLLATE utf8_unicode_ci NOT NULL,\n" +
                                "  `sex` char(1) COLLATE utf8_unicode_ci NOT NULL,\n" +
                                "  `jailing_date` date NOT NULL,\n" +
                                "  `crime_code` char(1) COLLATE utf8_unicode_ci NOT NULL,\n" +
                                "  `sentence_period` smallint(6) NOT NULL,\n" +
                                "  PRIMARY KEY (`id`),\n" +
                                "  UNIQUE KEY `nickname` (`nickname`)\n" +
                                ") ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci");
                stmt.executeUpdate("INSERT INTO `convict` VALUES "
                                + "(1,'axelF','M','2015-05-23','5',30),"
                                + "(2,'Lady_X','F','2014-08-05','6',240),"
                                + "(3,'Art3mis','F','2016-01-01','7',120),"
                                + "(4,'Parziv@1','M','2007-07-07','8',321)");
                firstTime = false;
            } catch (SQLException ex) {
                firstTime = true;
                model.addAttribute("errmsg", ex.getMessage());
                return "error";
            }
        }
        return listConvicts(model);
    }
    /*
     * This method will list all existing convicts.
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String listConvicts(ModelMap model) {
        List<Convict> convicts = service.findAllConvicts();
        if (convicts.isEmpty()) {
            model.addAttribute("errmsg", "The prison is empty. Let's start from the beginning! Push &quot;Try again&quot;");
            firstTime = true;
            return "error";
        }
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
