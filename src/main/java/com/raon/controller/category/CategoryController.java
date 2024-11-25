package com.raon.controller.category;

import com.raon.controller.category.request.CreateCategoryRequest;
import com.raon.controller.category.response.CreateCategoryResponse;
import com.raon.controller.category.request.UpdateCategoryRequest;
import com.raon.controller.category.response.UpdateCategoryResponse;
import com.raon.service.category.model.Category;
import com.raon.service.category.service.CategoryService;
import com.raon.service.user.model.UserSigninResult;
import com.raon.support.error.BoardException;
import com.raon.support.error.ErrorType;
import com.raon.support.response.ApiResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
        validateAdminUser(session);
        Category category = categoryService.create(request.toModel());
        return ApiResponse.success(new CreateCategoryResponse(category.name()));
    }

    @PutMapping("/{id}")
    public ApiResponse<UpdateCategoryResponse> updateCategory(
            @PathVariable Integer id,
            @RequestBody UpdateCategoryRequest request,
            HttpSession session
    ) {
        validateAdminUser(session);
        Category category = categoryService.update(request.toModel(id));
        return ApiResponse.success(new UpdateCategoryResponse(category.id(), category.name()));
    }

    private UserSigninResult validateAdminUser(HttpSession session) {
        Object sessionData = session.getAttribute(LOGIN_USER);
        if (sessionData instanceof UserSigninResult signinUser) {
            if (!signinUser.role().equals(ADMIN_USER)) {
                throw new BoardException(ErrorType.VALIDATION_ERROR);
            }
            return signinUser;
        } else {
            throw new BoardException(ErrorType.VALIDATION_ERROR);
        }
    }
}
