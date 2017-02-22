package sample.web.ui.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import sample.web.ui.crosscutting.MyExecutionTime;
import sample.web.ui.domain.*;
import sample.web.ui.repository.BaseOrderRepository;
import sample.web.ui.repository.MessageRepository;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sample.web.ui.repository.OrderRepository;
import sample.web.ui.repository.ProductCatalogRepository;

/**
 * @author Rob Winch
 * @author Doo-Hwan Kwak
 */
@Controller
@RequestMapping("/")
public class MessageController {

    @Autowired
    private final MessageRepository messageRepository;
    @Autowired
    private final OrderRepository orderRepository;
    @Autowired
    private final ProductCatalogRepository productCatalogRepository;
    @Autowired
    private final BaseOrderRepository baseOrderRepository;

    // Constructor
    public MessageController(MessageRepository messageRepository, OrderRepository orderRepository, ProductCatalogRepository productCatalogRepository, BaseOrderRepository baseOrderRepository) {
        this.messageRepository = messageRepository;
        this.orderRepository = orderRepository;
        this.productCatalogRepository = productCatalogRepository;
        this.baseOrderRepository = baseOrderRepository;
    }

    public void createProductCatalogAndProducts() {

        // build product catalog, two products, and order

        ProductCatalog productCatalog = new ProductCatalog();

        // right productCatalog: without id; left productCatalog: with id
        // (needed because of autoincrement)
        productCatalog = productCatalogRepository.save(productCatalog);

        // build add two products
        Product prod1 = new Product("Product 1", 500);
        Product prod2 = new Product("Product 2", 400);

        productCatalog.add(prod1);
        productCatalog.add(prod2);
    }

    public void createOrder() {

        // get the productCatalog
        ProductCatalog productCatalog = productCatalogRepository.findOne(1L);

        // "find" a product in the catalog and add it to the order
        Product prod = productCatalog.find(1);

        // make a copy of the product (the copy has no id yet)
        // why a copy is made?
        Product prodCopy = new Product(prod);

        Order order = new Order();
        order = orderRepository.save(order);
        order.add(prodCopy);

    }

    @Transactional
    @GetMapping
    @MyExecutionTime
    public ModelAndView list() {

        createProductCatalogAndProducts();

        Iterable<Message> messages = this.messageRepository.findAll();
        return new ModelAndView("messages/list", "messages", messages);
    }

    @GetMapping("{id}")
    public ModelAndView view(@PathVariable("id") Message message) {
        return new ModelAndView("messages/view", "message", message);
    }

    @Transactional
    @GetMapping(params = "form")
    public String createForm(@ModelAttribute Message message) {

        createOrder();
        decorateOrder();

        return "messages/form";
    }

    @PostMapping
    public ModelAndView create(@Valid Message message, BindingResult result,
                               RedirectAttributes redirect) {
        if (result.hasErrors()) {
            return new ModelAndView("messages/form", "formErrors", result.getAllErrors());
        }
        message = this.messageRepository.save(message);
        redirect.addFlashAttribute("globalMessage", "Successfully created a new message");
        return new ModelAndView("redirect:/{message.id}", "message.id", message.getId());
    }

    @RequestMapping("foo")
    public String foo() {
        throw new RuntimeException("Expected exception in controller");
    }

    @GetMapping(value = "delete/{id}")
    public ModelAndView delete(@PathVariable("id") Long id) {
        this.messageRepository.delete(id);
        Iterable<Message> messages = this.messageRepository.findAll();
        return new ModelAndView("messages/list", "messages", messages);
    }

    @GetMapping(value = "modify/{id}")
    public ModelAndView modifyForm(@PathVariable("id") Message message) {
        return new ModelAndView("messages/form", "message", message);
    }

    private void decorateOrder() {
        BaseOrder concreteOrder  = baseOrderRepository.findOne(1L);
        BaseOrder decoratedOrder1 = new OrderOption("wrapping paper", 7,
                concreteOrder);
        baseOrderRepository.save(decoratedOrder1);
        BaseOrder decoratedOrder2 = new OrderOption("nice box", 5, decoratedOrder1);
        baseOrderRepository.save(decoratedOrder2);
        BaseOrder decoratedOrder3 = new OrderOption("fast delivery", 12,
                decoratedOrder2);
        baseOrderRepository.save(decoratedOrder3);
        System.out.println("***** content of the order: " + decoratedOrder3);
        System.out.println("***** price of the order: " + decoratedOrder3.price());
    }


}
