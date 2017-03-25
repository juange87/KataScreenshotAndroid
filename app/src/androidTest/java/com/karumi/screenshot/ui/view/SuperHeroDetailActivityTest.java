package com.karumi.screenshot.ui.view;

import android.app.Activity;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;

import com.karumi.screenshot.ScreenshotTest;
import com.karumi.screenshot.SuperHeroesApplication;
import com.karumi.screenshot.di.MainComponent;
import com.karumi.screenshot.di.MainModule;
import com.karumi.screenshot.model.SuperHero;
import com.karumi.screenshot.model.SuperHeroesRepository;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;

import it.cosenonjaviste.daggermock.DaggerMockRule;

import static org.mockito.Mockito.when;

public class SuperHeroDetailActivityTest extends ScreenshotTest {

    @Rule
    public DaggerMockRule<MainComponent> daggerRule =
            new DaggerMockRule<>(MainComponent.class, new MainModule()).set(
                    new DaggerMockRule.ComponentSetter<MainComponent>() {
                        @Override
                        public void setComponent(MainComponent component) {
                            SuperHeroesApplication app =
                                    (SuperHeroesApplication) InstrumentationRegistry.getInstrumentation()
                                            .getTargetContext()
                                            .getApplicationContext();
                            app.setComponent(component);
                        }
                    });

    @Rule
    public ActivityTestRule<SuperHeroDetailActivity> activityRule =
            new ActivityTestRule<>(SuperHeroDetailActivity.class, true, false);

    @Mock
    SuperHeroesRepository repository;

    @Test
    public void showsASuperHero() {
        final SuperHero superHero = givenThereIsASuperHero(false);

        Activity activity = startActivity(superHero);

        compareScreenshot(activity);
    }

    @Test
    public void showsASuperHeroWithoutName() {
        final SuperHero superHero = givenThereIsASuperHeroWithoutName();

        Activity activity = startActivity(superHero);

        compareScreenshot(activity);
    }

    private SuperHero givenThereIsASuperHeroWithoutName() {
        String superHeroName = "";
        String superHeroDescription = "Super Hero Description";
        SuperHero superHero = new SuperHero(superHeroName, null, false, superHeroDescription);
        when(repository.getByName(superHeroName)).thenReturn(superHero);
        return superHero;
    }


    private SuperHero givenThereIsASuperHero(boolean isAvenger) {
        String superHeroName = "SuperHero";
        String superHeroDescription = "Super Hero Description";
        SuperHero superHero = new SuperHero(superHeroName, null, isAvenger, superHeroDescription);
        when(repository.getByName(superHeroName)).thenReturn(superHero);
        return superHero;
    }

    private SuperHeroDetailActivity startActivity(SuperHero superHero) {
        Intent intent = new Intent();
        intent.putExtra("super_hero_name_key", superHero.getName());
        return activityRule.launchActivity(intent);
    }
}
