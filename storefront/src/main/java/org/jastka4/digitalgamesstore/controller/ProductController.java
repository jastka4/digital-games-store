package org.jastka4.digitalgamesstore.controller;

import org.jastka4.digitalgamesstore.data.ProductData;
import org.jastka4.digitalgamesstore.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Resource
    private ProductService productService;

    @RequestMapping({"/all"})
    public String listProductHandler(final Model model,
                                     @RequestParam("page") final Optional<Integer> page,
                                     @RequestParam("size") final Optional<Integer> size) {
        final int currentPage = page.orElse(1);
        final int pageSize = size.orElse(3);

        final Page<ProductData> results = productService.getPaginated(PageRequest.of(currentPage - 1, pageSize));
        model.addAttribute("products", results);

        final int totalPages = results.getTotalPages();
        if (totalPages > 0) {
            final List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().toList();
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return "product/list";
    }
}
