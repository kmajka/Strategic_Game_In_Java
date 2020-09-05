package com.bwizard.cegame.window.screen.provider;

import com.bwizard.cegame.documents.providers.ConfigurationProvider;
import com.bwizard.cegame.window.screen.interfaces.IWindowScreen;

public class ScreenWindowFactory {

    private ConfigurationProvider configurationProvider;

    public ScreenWindowFactory(ConfigurationProvider configurationProvider) {
        this.configurationProvider = configurationProvider;
    }

    public IWindowScreen createWindow() {

        if (configurationProvider.getVideoConfiguration().isWindowedMode()) {
            return new SimpleScreenWindow(configurationProvider);
        }

        return new FullScreenWindow(configurationProvider);
    }
}
