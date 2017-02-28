package controllers.productcatalog;


import changetitle.ChangeTitleControllerComponent;
import com.commercetools.sunrise.framework.components.PageHeaderControllerComponentSupplier;
import com.commercetools.sunrise.framework.controllers.cache.NoCache;
import com.commercetools.sunrise.framework.controllers.metrics.LogMetrics;
import com.commercetools.sunrise.framework.hooks.RegisteredComponents;
import com.commercetools.sunrise.framework.reverserouters.productcatalog.HomeReverseRouter;
import com.commercetools.sunrise.framework.template.TemplateControllerComponentsSupplier;
import com.commercetools.sunrise.framework.template.engine.TemplateRenderer;
import com.commercetools.sunrise.productcatalog.home.HomeRecommendationsControllerComponent;
import com.commercetools.sunrise.productcatalog.home.SunriseHomeController;
import com.commercetools.sunrise.productcatalog.home.viewmodels.HomePageContentFactory;
import githubfeed.GitHubFeedControllerComponent;
import play.mvc.Result;

import javax.annotation.Nullable;
import javax.inject.Inject;
import java.util.concurrent.CompletionStage;

@LogMetrics
@NoCache
@RegisteredComponents({
        TemplateControllerComponentsSupplier.class,
        PageHeaderControllerComponentSupplier.class,
        HomeRecommendationsControllerComponent.class,
        GitHubFeedControllerComponent.class
})
public final class HomeController extends SunriseHomeController {

    private final HomeReverseRouter homeReverseRouter;

    @Inject
    public HomeController(final TemplateRenderer templateRenderer,
                          final HomePageContentFactory pageContentFactory,
                          final HomeReverseRouter homeReverseRouter) {
        super(templateRenderer, pageContentFactory);
        this.homeReverseRouter = homeReverseRouter;
    }

    @Override
    public String getTemplateName() {
        return "home";
    }

    @Nullable
    @Override
    public String getCmsPageKey() {
        return "home";
    }

    public CompletionStage<Result> index() {
        return redirectTo(homeReverseRouter.homePageCall());
    }
}
