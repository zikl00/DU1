package com.github.zikl00.adventura.ui;

public interface subjektVychody {

    public void zaregistruj(observerVychody observer);
    public void odregistruj(observerVychody observer);
    public void notifyZmenaVychodu();

}
