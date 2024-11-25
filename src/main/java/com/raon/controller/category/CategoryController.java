package com.raon.controller.category;

import com.raon.controller.category.request.CreateCategoryRequest;
import com.raon.controller.category.request.CreateCategoryResponse;
import com.raon.service.category.model.Category;
import com.raon.service.category.service.CategoryService;
import com.raon.service.user.model.UserSigninResult;
import com.raon.support.error.BoardException;
import com.raon.support.error.ErrorType;
import com.raon.support.response.ApiResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.raon.controller.user.UserController.LOGIN_USER;
import static com.raon.domain.user.UserConstant.ADMIN_USER;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public ApiResponse<CreateCategoryResponse> createCategory(
            @RequestBody CreateCategoryRequest request,
            HttpSession session
    ) {
        // TODO AOP기반으로 검증 로직 리팩토링
        Object sessionData = session.getAttribute(LOGIN_USER);
        if (sessionData instanceof UserSigninResult signinUser) {
            if (!signinUser.role().equals(ADMIN_USER)) {
                throw new BoardException(ErrorType.VALIDATION_ERROR);
            }

            Category category = categoryService.create(request.toModel());
            return ApiResponse.success(new CreateCategoryResponse(category.name()));
        } else {
            throw new BoardException(ErrorType.VALIDATION_ERROR);
        }

    }
}
