package org.jastka4.digitalgamesstore.controller;

import org.jastka4.digitalgamesstore.data.ProductData;
import org.jastka4.digitalgamesstore.forms.ProductForm;
import org.jastka4.digitalgamesstore.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private static final Logger LOG = LoggerFactory.getLogger(AdminController.class);

    private static final String PRODUCT_EDIT = "product/edit";

    @Resource
    private ProductService productService;

    @GetMapping("/product/new")
    public String create(final Model model) {
        model.addAttribute("productForm", new ProductForm());
        return PRODUCT_EDIT;
    }

    @GetMapping("/product/edit")
    public String edit(final Model model, @RequestParam(value = "code") final String code) {
        ProductForm productForm = null;

        if (!StringUtils.isEmpty(code)) {
            ProductData product = productService.getByCodeAndOnlineCatalogue(false, code);
            if (product != null) {
                productForm = new ProductForm(product);
            }
        }
        if (productForm == null) {
            productForm = new ProductForm();
        }
        model.addAttribute("productForm", productForm);
        return PRODUCT_EDIT;
    }

    @PostMapping("/product/update")
    public String update(final Model model,
                         @ModelAttribute("productForm") @Validated final ProductForm productForm,
                         final BindingResult result) {
        if (result.hasErrors()) {
            return PRODUCT_EDIT;
        }

        try {
            productService.updateOrCreate(productForm);
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);

            model.addAttribute("errorMessage", e.getMessage());
            return PRODUCT_EDIT;
        }

        return "redirect:/admin/product/all";
    }

    @GetMapping("/product/sync")
    public String sync(@RequestParam(value = "code") final String code) {
        productService.sync(code);
        return "redirect:/admin/product/all";
    }

    @GetMapping("/product/all")
    public String listAll(final Model model,
                          @RequestParam("page") final Optional<Integer> page,
                          @RequestParam("size") final Optional<Integer> size) {
        final int currentPage = page.orElse(1);
        final int pageSize = size.orElse(3);

        final Page<ProductData> results = productService.getPaginated(
                PageRequest.of(currentPage - 1, pageSize, Sort.by("name").and(Sort.by("onlineCatalogue").descending())));
        model.addAttribute("products", results);

        final int totalPages = results.getTotalPages();
        if (totalPages > 0) {
            final List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().toList();
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return "product/list";
    }
}
