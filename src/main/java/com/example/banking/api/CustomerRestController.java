package com.example.banking.api;

import com.example.banking.model.Customers;
import com.example.banking.service.CustomerService;
import com.example.banking.service.customer.CustomerSaveRequest;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
@AllArgsConstructor
public class CustomerRestController {
    private final CustomerService customerService;

    @GetMapping
////    public List<Customers> findAll() {
////        return customerService.findAll();
////    }
    public Page<Customers> findAll(@RequestParam(defaultValue = "") @PageableDefault(size = 10)Pageable pageable) {
        return customerService.findAll(pageable);
    }
//@GetMapping
//public ModelAndView findAll(@RequestParam(defaultValue = "") @PageableDefault(size = 10) Pageable pageable) {
//    Page<Customers> customers = customerService.findAll(pageable);
//    ModelAndView modelAndView = new ModelAndView("home");
//    modelAndView.addObject("customers", customers);
//    return modelAndView;
//}

    @PostMapping
    public ResponseEntity<?> create(@RequestBody CustomerSaveRequest request) {
        customerService.create(request);
        return ResponseEntity.ok(request);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getById( @PathVariable Long id) {
        Customers customer = customerService.findById(id);
        return ResponseEntity.ok(customer);
    }
    @PatchMapping("{id}")
    public ResponseEntity<?> update(@RequestBody CustomerSaveRequest request, @PathVariable Long id) {
        Customers customer = customerService.update(request, id);
        return ResponseEntity.ok(customer);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        customerService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
