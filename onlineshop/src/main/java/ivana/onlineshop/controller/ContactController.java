package ivana.onlineshop.controller;

import ivana.onlineshop.entity.Contact;
import ivana.onlineshop.entity.MyUser;
import ivana.onlineshop.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ContactController {

    @Autowired
    ContactRepository contactRepository;

    @GetMapping("/contact-message")
    public String getContact(Model model, String keyword) {
        model.addAttribute("contacts", contactRepository.searchContact(keyword));
        return "/contact-message";
    }

    @GetMapping("/contact")
    public String saveContact(Model model) {
        model.addAttribute("contactObject", new Contact());
        return "contact";
    }

    @PostMapping("/contact")
    public String saveContact2(@ModelAttribute Contact contact, Model model, MyUser user) throws Exception {
        model.addAttribute("contactObject", contact);
        if (contact.getUsername().equals((user.getUsername()))){
            contactRepository.save(contact);
            return "message-sent-succesful";
        } else
            throw new Exception("Wrong username");
    }

    @RequestMapping(path = "/delete-contact/{id}")
    public String deleteContact(@PathVariable("id") Integer id) {
        contactRepository.deleteById(id);
        return "redirect:/contact-message";
    }
}
