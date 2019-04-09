package ltd.jezhu.promets.exception;

import ltd.jezhu.promets.dto.base.io.OutParam;
import ltd.jezhu.promets.dto.base.io.Response;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器
 * @author ymzhu
 * @date 2019/3/26 10:35
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 应用到所有@RequestMapping注解方法，在其执行之前初始化数据绑定器
     * @param binder binder
     * @author ymzhu
     * @date 2019/3/26 10:31
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
    }

    /**
     * 把值绑定到Model中，使全局@RequestMapping可以获取到该值
     * @param model model
     * @author ymzhu
     * @date 2019/3/26 10:32
     */
    @ModelAttribute
    public void addAttributes(Model model) {
    }

    /**
     * 全局异常捕捉处理
     * @param e e
     * @return {@link OutParam}
     * @author ymzhu
     * @date 2019/3/26 10:32
     */
    @ExceptionHandler(value = Exception.class)
    public OutParam errorHandler(Exception e) {
        e.printStackTrace();
        if (e instanceof InvalidTokenException) {
            return Response.unauthorized(e.getMessage());
        } else if (e instanceof ServiceException) {
            return Response.exception(e.getMessage());
        } else if (e instanceof DaoException) {
            return Response.exception(e.getMessage());
        } else {
            return Response.exception();
        }
    }
}
