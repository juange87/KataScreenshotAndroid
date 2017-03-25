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

    @Test
    public void showsASuperHeroWithoutDescription() {
        final SuperHero superHero = givenThereIsASuperHeroWithoutDescription();

        Activity activity = startActivity(superHero);

        compareScreenshot(activity);
    }

    @Test
    public void showsASuperHeroWithLongName() {
        final SuperHero superHero = givenThereIsASuperHeroWithLongName();

        Activity activity = startActivity(superHero);

        compareScreenshot(activity);
    }

    @Test
    public void showsASuperHeroWithLongDescription() {
        final SuperHero superHero = givenThereIsASuperHeroWithLongDescription();

        Activity activity = startActivity(superHero);

        compareScreenshot(activity);
    }

    private SuperHero givenThereIsASuperHeroWithLongDescription() {
        String superHeroName = "SuperHero";
        String superHeroDescription =
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt "
                        + "ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation "
                        + "ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in "
                        + "reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. "
                        + "Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt "
                        + "mollit anim id est laborum.";
        SuperHero superHero = new SuperHero(superHeroName, null, false, superHeroDescription);
        when(repository.getByName(superHeroName)).thenReturn(superHero);
        return superHero;
    }

    private SuperHero givenThereIsASuperHeroWithLongName() {
        String superHeroName =
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt "
                        + "ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation "
                        + "ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in "
                        + "reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. "
                        + "Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt "
                        + "mollit anim id est laborum.";
        String superHeroDescription = "Super Hero Description";
        SuperHero superHero = new SuperHero(superHeroName, null, false, superHeroDescription);
        when(repository.getByName(superHeroName)).thenReturn(superHero);
        return superHero;
    }

    private SuperHero givenThereIsASuperHeroWithoutDescription() {
        String superHeroName = "SuperHero";
        SuperHero superHero = new SuperHero(superHeroName, null, false, null);
        when(repository.getByName(superHeroName)).thenReturn(superHero);
        return superHero;
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
