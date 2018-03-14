package com.github.zikl00.adventura.logika;

import java.util.*;
/**
 *  Class HerniPlan - třída představující mapu a stav adventury.
 * 
 *  Tato třída inicializuje prvky ze kterých se hra skládá:
 *  vytváří všechny prostory, propojuje je vzájemně pomocí východů 
 *  a pamatuje si aktuální prostor, ve kterém se hráč právě nachází.
 *  Jsou zde nadefinovány postavy a předměty a jejich umístění.
 *
 *@author     Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova, Libor Zíka
 *@version    pro školní rok 2015/2016
 */
public class HerniPlan {
    
    private Prostor aktualniProstor;
    private Hrdinka hrdinka;
    private boolean naslaBratra = false;
    private Hra hra;
    
     /**
     *  Konstruktor který vytváří jednotlivé prostory a propojuje je pomocí východů.
     *  Jako výchozí aktuální prostor nastaví zničenou vesnici.
     */
    public HerniPlan(Hra hra) {
        this.hra = hra;
        zalozProstoryHry();
    }
    
    /**
     *  Vytváří jednotlivé prostory a generuje mezi nimi propojení pomocí východů.
     *  Definuje postavy a předměty a jejich umístění.
     *  Jako výchozí aktuální prostor nastaví zničenou vesnici.
     */
    private void zalozProstoryHry() {
        // vytvářejí se jednotlivé prostory
        
        Prostor znicenaVesnice = new Prostor(0, "zničená_vesnice","zničená vesnice, s rozpadlými vypálenými domky\nTaková spoušť, kdo to jen mohl udělat a proč");
        //--------------------------------------------------------------------------------------------------------
        List<Prostor> level1 = new ArrayList<Prostor>();
        Prostor pesina = new Prostor(1, "pěšina","prašná pěšina, která vede kolem vesničky.\nKolem jsou zasazené kytičky");
        Prostor borovicovyLes = new Prostor(1, "borovicový_les","borovicový les, docela hustý.\nMoc pěkně voní. Určitě bude plný šišek");
        Prostor kamennaCesta = new Prostor(1, "kamenná_cesta","kamenná cesta, asi pro kočáry.\nSem tam je nějaká díra, asi už se moc nepoužívá");
        Prostor rozborenyDomek = new Prostor(1, "rozbořený_domek","rozbořený domek. Možná se v něm dá něco najít");
        Prostor louka = new Prostor (1, "louka","žlutá louka s vysokou trávou.\nSnad se tam nikdo neschovává");
        Prostor propast = new Prostor (1, "propast","propast, velmi prudký sešup dolů do tmy");
        level1.add(pesina);
        level1.add(borovicovyLes);
        level1.add(kamennaCesta);
        level1.add(rozborenyDomek);
        level1.add(louka);
        level1.add(propast);
        //--------------------------------------------------------------------------------------------------------
        List<Prostor> level2 = new ArrayList<Prostor>();
        Prostor reka = new Prostor(2, "řeka","řeka, krásně čirá plná kamení, přes kterou vede visutý most");
        Prostor hrbitov = new Prostor(2, "hřbitov","hřbitov, pěkně starý, plný zanedbaných a polorozbořených hrobů");
        Prostor rybnik = new Prostor(2, "rybník","rybník, celkem čistý, se zarosltým ostrůvkem uprostřed");
        Prostor lesniCesta = new Prostor(2, "lesní_cesta","lesní cesta, úzká a plná kořenů, snad nezakopneš");
        Prostor temnyHvozd = new Prostor(2, "temný_hvozd","temný hvozd, jdi raději rychle než-li pomalu");
        Prostor smrkovyLes = new Prostor(2, "smrkový_les","smrkový les, krásný a čistý, ze stříbrných smrků.\nUrčitě je plný hub, snad tady nebude vlk");
        Prostor chata = new Prostor(2, "chata","chata, zvenku vypadá neudržovaná");
        level2.add(reka);
        level2.add(hrbitov);
        level2.add(rybnik);
        level2.add(lesniCesta);
        level2.add(temnyHvozd);
        level2.add(smrkovyLes);
        level2.add(chata);
        //special - místnosti, které se vždy stejně propojí východy tak, aby to dávalo smysl
        Prostor kobka = new Prostor(2, "kobka","stará kobka, určitě v ní není nic zajímavého");
        Prostor podzemi = new Prostor(2, "podzemí","podzemí, je to tu cítit hnilobou a rozkladem");
        Prostor ostruvek = new Prostor(2, "ostrůvek","ostrůvek, malý a zarostlý, kdo by tady co dělal");
        Prostor jeskyne = new Prostor(2, "jeskyně","jeskyně, malá a temná, akorát pro jednoho.\nAle je tu celkem teplo a útulno");
        Prostor novyDomek = new Prostor(2, "nový_domek","nový domek, vypadá to, že si ho tu někdo nedávno postavil.\nZ pěkným kamenných bloků, to asi nebyla lehká práce");
        Prostor obyvak = new Prostor(2, "obývák","obývací pokoj, malý ale útulný, s krbem uprostřed stěny.\nA s chrápajícím trpaslíkem v křesle.");
        Prostor knihovna = new Prostor(2, "knihovna","knihovna, ušmudlaná a plná starých knih, kdo se v nich vyzná");
        Prostor sklep = new Prostor(2, "sklep","je tu tma a..");
        //--------------------------------------------------------------------------------------------------------
        //místnosti úrovně 3
        List<Prostor> level3 = new ArrayList<Prostor>();
        Prostor strasidelnyZamek = new Prostor(3, "strašidelný_zámek","zámek, vypadá opravdu strašidelně");
        Prostor opustenyTabor = new Prostor(3, "opuštěný_tábor","opuštěný tábor. Asi tu někdo nedávno byl, ještě doutná oheň");
        Prostor zricenina = new Prostor(3, "zřícenina","zřícenina, snad mi to nespadne na hlavu dřív než se porozhlédnu");
        Prostor stareVezeni = new Prostor(3, "staré_vězení","staré vězení, odsud už určitě všichni utekli");
        level3.add(strasidelnyZamek);
        level3.add(opustenyTabor);
        level3.add(zricenina);
        level3.add(stareVezeni);
        //special - místnosti, které se vždy stejně propojí východy tak, aby to dávalo smysl
        Prostor nadvori = new Prostor(3, "nádvoří","nádvoří, stejně tak strašidelné jako celý tenhle zámek");
        Prostor kanal = new Prostor(3, "kanál","kanál, pěkně to tam smrdí a je tam pěkná tma");
        Prostor postranniChodba = new Prostor(3, "postranní_chodba","postranní chodba v kanálu, ideální místo na schovku");
        Prostor sklad = new Prostor(3, "sklad","sklad, možná tady bude nějaká výbava");
        Prostor stan = new Prostor(3, "stan","stan, vypadá to, jako když tu někdo bydlí");
        Prostor skrys = new Prostor(3, "skrýš","skřýš, tady si určitě někdo něco schovává..");
        Prostor tajnaMistnost = new Prostor(3, "tajná_místnost","tajná místnost, už moc tajná není");
        Prostor prvniCela = new Prostor(3, "první_cela","první cela, pár zrezlých železných tyčí, kbelík a postel");
        Prostor druhaCela = new Prostor(3, "druhá_cela","druhá cela, je tu jen plesnivá postel a roztrhaná bota");
        //--------------------------------------------------------------------------------------------------------
        //místnosti úrovně 4
        Prostor zamcenaChodba = new Prostor(4, "zamčená_chodba","zamčená chodba, kam asi může vést?");
        Prostor kouzelnikovaKomnata = new Prostor(4, "kouzelníkova_komnata","komnata se zlým kouzelníkem a s tvým bratrem, kterého unesl");
        //--------------------------------------------------------------------------------------------------------
        //přiřazují se průchody mezi prostory (sousedící prostory)
        //pro level 1
        while(znicenaVesnice.getVychody().size()<2){
            for(Prostor prostor : level1){
                if (randInt() > 60){
                    znicenaVesnice.setVychod(prostor);
                    prostor.setVychod(znicenaVesnice);
                }
            }
        }
        for(Prostor prostor : level1){
            if(prostor.getVychody().size() == 0){
                while(prostor.getVychody().size() == 0){
                   for(Prostor prostorSVychody : level1){
                       if(prostorSVychody.getVychody().size() != 0 && randInt() > 75){
                           prostorSVychody.setVychod(prostor);
                           prostor.setVychod(prostorSVychody);
                           break;//aby se v cyklu nahodou nepriradila oblast sama sobe :D
                        }
                    }
                }
            }
        }
        //--------------------------------------------------------------------------------------------------------
        //pro level 2
        for(Prostor prostor : level2){
            if(prostor.getVychody().size() == 0){
                while(prostor.getVychody().size() == 0){
                   for(Prostor prostorlvl1 : level1){
                        if (randInt() > 75){
                           prostorlvl1.setVychod(prostor);
                           prostor.setVychod(prostorlvl1);
                           break;//aby se v cyklu nahodou nepriradila oblast sama sobe :D
                        }
                   }
                }
            }
        }
        //ze hrbitova do kobky a do podzemi
        hrbitov.setVychod(kobka);
        kobka.setVychod(hrbitov);
        kobka.setVychod(podzemi);
        podzemi.setVychod(kobka);
        //z rybnika na ostruvek a do jeskyne
        ostruvek.setVychod(rybnik);
        rybnik.setVychod(ostruvek);
        ostruvek.setVychod(jeskyne);
        jeskyne.setVychod(ostruvek);
        //pres reku do noveho domku a do obyvaku se sklepen nebo do knihovny
        reka.setVychod(novyDomek);
        novyDomek.setVychod(reka);
        novyDomek.setVychod(obyvak);
        obyvak.setVychod(novyDomek);
        sklep.setVychod(obyvak);
        obyvak.setVychod(sklep);
        novyDomek.setVychod(knihovna);
        knihovna.setVychod(novyDomek);
        //--------------------------------------------------------------------------------------------------------
        //pro level 3
        for(Prostor prostor : level3){
            if(prostor.getVychody().size() == 0){
                while(prostor.getVychody().size() == 0){
                   for(Prostor prostorlvl2 : level2){
                        if (randInt() > 75){
                           prostorlvl2.setVychod(prostor);
                           prostor.setVychod(prostorlvl2);
                           break;//aby se v cyklu nahodou nepriradila oblast sama sobe :D
                        }
                   }
                }
            }
        }
        //ze zamku na nadvori nebo sklad, z nadvori do kanalu a do postranni chodby
        strasidelnyZamek.setVychod(nadvori);
        nadvori.setVychod(strasidelnyZamek);
        strasidelnyZamek.setVychod(sklad);
        sklad.setVychod(strasidelnyZamek);
        nadvori.setVychod(kanal);
        kanal.setVychod(nadvori);
        kanal.setVychod(postranniChodba);
        postranniChodba.setVychod(kanal);
        //z taboru do stanu
        opustenyTabor.setVychod(stan);
        stan.setVychod(opustenyTabor);
        //ze zriceniny do skryse nebo to tajne mistnosti
        zricenina.setVychod(skrys);
        skrys.setVychod(zricenina);
        zricenina.setVychod(tajnaMistnost);
        tajnaMistnost.setVychod(zricenina);
        //ze stareho vezeni do prvni a druhe cely
        stareVezeni.setVychod(prvniCela);
        prvniCela.setVychod(stareVezeni);
        stareVezeni.setVychod(druhaCela);
        druhaCela.setVychod(stareVezeni);
        //--------------------------------------------------------------------------------------------------------
        //pro level 4
        while(zamcenaChodba.getVychody().size() == 0){
            for(Prostor prostorlvl2 : level2){
                if (randInt() > 75){
                    prostorlvl2.setVychod(zamcenaChodba);
                    zamcenaChodba.setVychod(prostorlvl2);
                    break;//aby se v cyklu nahodou nepriradila oblast sama sobe :D
                }
            }
        }
        zamcenaChodba.setVychod(kouzelnikovaKomnata);
        kouzelnikovaKomnata.setVychod(zamcenaChodba);
        //--------------------------------------------------------------------------------------------------------
        //pro pripomenuti, format kontruktoru veci a postav
        //vec:          (String nazev, boolean prenositelnost, int utocneCislo)
        //postava:      (String nazev, boolean friend, int zivoty, int utocneCislo)
        //seznam prostor    lvl1: pesina, borovicovyLes, kamennaCesta, rozborenyDomek, louka, propast
        //--------------------------------------------------------------------------------------------------------
        //postavy, tvorba a umisteni spolu s vecmi, take tvorba a umisteni
        
        //znicenaVesnice
        Postava goblin = new Postava ("goblin", false, 8, 6);
        znicenaVesnice.vlozPostavu(goblin);
        Vec dyka = new Vec ("dýka", true, 1);
        goblin.vlozVec(dyka);
        Vec lecivaLahvicka = new Vec ("léčivá_lahvička", true, -4);
        goblin.vlozVec(lecivaLahvicka);
        Vec mrtvola = new Vec ("mrtvola", false, -10);
        znicenaVesnice.vlozVec(mrtvola);
        
        //pesina
        Postava vesnican = new Postava ("vesničan", true, 10, 5);
        pesina.vlozPostavu(vesnican);
        vesnican.setKecy("Všechny nás zabijou, utíkeeej!!!");
        
        //borovicovyLes
        Postava lesniImp = new Postava ("lesní imp", false, 7, 5);
        borovicovyLes.vlozPostavu(lesniImp);
        Vec spadlyStrom = new Vec ("spadlý_strom", false, 4);
        borovicovyLes.vlozVec(spadlyStrom);
        
        //rozborenyDomek
        Postava zlodej1 = new Postava ("zloděj", false, 10, 7);
        rozborenyDomek.vlozPostavu(zlodej1);
        Vec palcat = new Vec ("palcát", true, 5);
        rozborenyDomek.vlozVec(palcat);
        Vec cihly = new Vec ("kopa_cihel", false, 30);
        rozborenyDomek.vlozVec(cihly);
        Vec elixir = new Vec ("elixír", true, -10);
        zlodej1.vlozVec(elixir);
        
        //kamennaCesta
        Postava loupeznik = new Postava ("loupežník", false, 10, 7);
        kamennaCesta.vlozPostavu(loupeznik);
        Vec mec = new Vec ("meč", true, 2);
        loupeznik.vlozVec(mec);
        
        //propast
        Postava skret = new Postava ("skřet", false, 12, 10);
        propast.vlozPostavu(skret);
        Vec palice = new Vec ("palice", true, 3);
        skret.vlozVec(palice);
        Vec lecivyLektvar = new Vec ("léčivý_lektvar", true, -8);
        propast.vlozVec(lecivyLektvar);
        Vec sud = new Vec ("těžký_sud", false, 50);
        propast.vlozVec(sud);
        
        //louka
        Postava zebrak = new Postava ("žebrák", true, 6, 3);
        louka.vlozPostavu(zebrak);
        zebrak.setKecy("Nějakej peníz by nebyl?");
        Vec balvan = new Vec ("balvan", false, 50);
        louka.vlozVec(balvan);
       
        //klice, tvorba a umisteni, pouze k prostorum lvl 2
        Vec klicHrbitov = new Vec ("klíč_od_hřbitova", true, 0);
        skret.vlozVec(klicHrbitov);
        hrbitov.setKlic("klíč_od_hřbitova");
        
        Vec klicChata = new Vec ("klíč_od_chaty", true, 0);
        lesniImp.vlozVec(klicChata);
        chata.setKlic("klíč_od_chaty");
        
        //--------------------------------------------------------------------------------------------------------
        //                  lvl2:           reka, hrbitov, rybnik, lesniCesta, temnyHvozd, smrkovyLes, chata
        //                  lvl2 special:   kobka, podzemi, ostruvek, jeskyne, novyDomek, obyvak, knihovna, sklep
        //reka
        
        //hrbitov
        Postava kostlivec = new Postava ("kostlivec", false, 10, 8);
        hrbitov.vlozPostavu(kostlivec);
        Vec leciveBylinky = new Vec ("léčivé_bylinky", true, -6);
        kostlivec.vlozVec(leciveBylinky);
        Vec hrob3 = new Vec ("hrob", false, 4);
        hrbitov.vlozVec(hrob3);
        Vec hrobka = new Vec ("hrobka", false, 4);
        hrbitov.vlozVec(hrobka);
        Vec mramorSloup = new Vec ("mramorový_sloup", false, 4);
        hrbitov.vlozVec(mramorSloup);
        
        //rybnik
        Postava vodnik = new Postava ("vodník", false, 8, 8);
        rybnik.vlozPostavu(vodnik);
        Vec vrba = new Vec ("stará_vrba", false, 4);
        rybnik.vlozVec(vrba);
        Vec lavicka = new Vec ("lavička", false, 4);
        rybnik.vlozVec(lavicka);
        
        //lesniCesta
        Postava kluk = new Postava ("ztracený klučina", true, 10, 5);
        lesniCesta.vlozPostavu(kluk);
        kluk.setKecy("Tenhle les je tak hluboký, určitě už taky nevíš odkud jsi přišla...");
        Vec koren = new Vec ("starý_kořen", false, 4);
        lesniCesta.vlozVec(koren);
        
        //temnyHvozd
        Postava raubir = new Postava ("raubíř", false, 7, 11);
        temnyHvozd.vlozPostavu(raubir);
        
        //smrkovyLes
        Postava lesniVila = new Postava ("lesní víla", true, 10, 5);
        smrkovyLes.vlozPostavu(lesniVila);
        lesniVila.setKecy("Ano, viděla jsem tvého bratra, ale bylo to už dávno a bylo to daleko. Hihihi.");
        Vec parez = new Vec ("pařez", false, 4);
        smrkovyLes.vlozVec(parez);
        Vec houby = new Vec ("houby", true, -4);
        smrkovyLes.vlozVec(houby);
        
        //chata
        Vec ostryMec = new Vec ("ostrý_meč", true, 4);
        chata.vlozVec(ostryMec);
        Vec obriMec = new Vec ("obří_meč", false, 4);
        chata.vlozVec(obriMec);
        Vec propadleKreslo = new Vec ("propadlé_křeslo", false, 4);
        chata.vlozVec(propadleKreslo);
        
        ////klice, tvorba a umisteni, pouze k prostorum lvl 2 a lvl 3
        Vec klicKobka = new Vec ("klíč_od_kobky", true, 0);
        temnyHvozd.vlozVec(klicKobka);
        kobka.setKlic("klíč_od_kobky");
        
        //kobka
        Postava ozivlaKostra = new Postava ("oživlá kostra", false, 10, 11);
        kobka.vlozPostavu(ozivlaKostra);
        Vec masticka = new Vec ("mastička", true, -6);
        ozivlaKostra.vlozVec(masticka);
        Vec hrob = new Vec ("bílý_hrob", false, 5);
        kobka.vlozVec(hrob);
        
        //podzemi
        Vec sekyra = new Vec ("sekyra", true, 5);
        podzemi.vlozVec(sekyra);
        Vec hrob2 = new Vec ("rozbořený_hrob", false, 5);
        podzemi.vlozVec(hrob2);
        
        //ostruvek
        
        //jeskyne
        Postava tulak = new Postava ("tulák", true, 10, 6);
        jeskyne.vlozPostavu(tulak);
        tulak.setKecy("Máš štěstí, vím kde je tvůj bratr, unesl ho zlý čaroděj.\nVchod do jeho komnaty je ale zamčený. Musíš najít klíč.");
        Vec stul = new Vec ("stůl", false, 5);
        jeskyne.vlozVec(stul);
        Vec kamennaPostel = new Vec ("kamenná_postel", false, 5);
        jeskyne.vlozVec(kamennaPostel);
        
        //novyDomek
        Vec komoda = new Vec ("komoda", false, 3);
        novyDomek.vlozVec(komoda);
        Vec zrcadlo = new Vec ("zrcadlo", false, 3);
        novyDomek.vlozVec(zrcadlo);
        Vec botnik = new Vec ("botník", false, 3);
        novyDomek.vlozVec(botnik);
        
        //obyvak
        Postava trpaslik = new Postava ("trpaslík", true, 12, 8);
        obyvak.vlozPostavu(trpaslik);
        trpaslik.setKecy("V tyhle dny bych se tu v okolí moc venku nezdržoval. Radši tu zůstaň, ve sklepě mám nějaké jídlo.");
        Vec stul2 = new Vec ("stůl", false, 3);
        obyvak.vlozVec(stul2);
        Vec kreslo = new Vec ("křeslo", false, 3);
        obyvak.vlozVec(kreslo);
        Vec krb = new Vec ("velký_krb", false, 3);
        obyvak.vlozVec(krb);
        
        //knihovna
        Vec skrin = new Vec ("skříň", false, 3);
        knihovna.vlozVec(skrin);
        Vec knihovnicka = new Vec ("knihovnička", false, 3);
        knihovna.vlozVec(knihovnicka);
        Vec kreslo2 = new Vec ("křesílko", false, 3);
        knihovna.vlozVec(kreslo2);
        //rovnou sem dám i klíč
        Vec klicSklep = new Vec ("klíč_od_sklepa", true, 0);
        knihovna.vlozVec(klicSklep);
        sklep.setKlic("klíč_od_sklepa");
        
        //sklep
        Vec chleba = new Vec ("chleba", true, -5);
        sklep.vlozVec(chleba);
        Vec jablko = new Vec ("jablko", true, -3);
        sklep.vlozVec(jablko);
        Vec vino = new Vec ("víno", true, -12);
        sklep.vlozVec(vino);
        Vec plnaBednaJidla = new Vec ("plná_bedna_jídla", false, -3);
        sklep.vlozVec(plnaBednaJidla);
        
        //--------------------------------------------------------------------------------------------------------
        //                  lvl3:           strasidelnyZamek, opustenyTabor, zricenina, stareVezeni
        //                  lvl3 special:   nadvori, kanal, postranniChodba, sklad, stan, skrys, tajnaMistnost, prvniCela, druhaCela
        //strasidelnyZamek
        
        //opustenyTabor
        Vec rozbityStan = new Vec ("rozbitý_stan", false, -3);
        opustenyTabor.vlozVec(rozbityStan);
        
        //zricenina
        Postava zombie = new Postava ("zombie", false, 14, 13);
        zricenina.vlozPostavu(zombie);
        Vec rohlik = new Vec ("rohlík", true, -3);
        zombie.vlozVec(rohlik);
        Vec kameni = new Vec ("kamení", false, -1);
        zricenina.vlozVec(kameni);
        
        //stareVezeni
        Postava vlk = new Postava ("hrozivý vlk", false, 18, 13);
        stareVezeni.vlozPostavu(vlk);
        Vec tupyMec = new Vec ("tupý_meč", true, 1);
        stareVezeni.vlozVec(tupyMec);
        Vec oldStul = new Vec ("starý_stůl", false, -1);
        stareVezeni.vlozVec(oldStul);
        
        //nadvori
        Postava duch = new Postava ("duch", true, 12, 12);
        nadvori.vlozPostavu(duch);
        duch.setKecy("Zaživa jsem sloužil čarodějovi, ale když už mě nepotřeboval, tak ze mě udělal tohle.\nVe skrýši by měl být klíč k jeho komnatě.");
        Vec dlazba = new Vec ("dlažební_kostky", false, -1);
        nadvori.vlozVec(dlazba);
        Vec pomnik = new Vec ("pomník", false, -1);
        nadvori.vlozVec(pomnik);
        Vec socha2 = new Vec ("mramorová_socha", false, -1);
        nadvori.vlozVec(socha2);
        
        //kanal
        Postava obriKrysa = new Postava ("obří krysa", false, 12, 12);
        kanal.vlozPostavu(obriKrysa);
        Vec oschlyRohlik = new Vec ("oschlý_rohlík", true, -1);
        kanal.vlozVec(oschlyRohlik);
        
        //postranniChodba
        Postava zahalenaPostava = new Postava ("zahalená postava", true, 12, 12);
        postranniChodba.vlozPostavu(zahalenaPostava);
        Vec prazdnaBedna = new Vec ("prázdná_bedna", false, -1);
        postranniChodba.vlozVec(prazdnaBedna);
        
        //sklad
        Vec krabice = new Vec ("krabice", false, 6);
        sklad.vlozVec(krabice);
        Vec krabice1 = new Vec ("krabička", false, 6);
        sklad.vlozVec(krabice1);
        Vec malaBedna = new Vec ("malá_bedna", false, 6);
        sklad.vlozVec(malaBedna);
        Vec velkaBedna = new Vec ("velká_bedna", false, 6);
        sklad.vlozVec(velkaBedna);
        
        //stan
        Postava vrahoun = new Postava ("vrahoun", false, 10, 14);
        stan.vlozPostavu(vrahoun);
        Vec dlouhyMec = new Vec ("dlouhý_meč", true, 6);
        vrahoun.vlozVec(dlouhyMec);
        Vec shnilaPerina = new Vec ("shnilá_peřina", false, 6);
        stan.vlozVec(shnilaPerina);
        
        //skrys
        Postava skritek = new Postava ("skřítek", false, 16, 12);
        skrys.vlozPostavu(skritek);
        Vec loupak = new Vec ("loupák", true, -3);
        skritek.vlozVec(loupak);
        //rovnou sem dám i klíč
        Vec klicZamcenaChodba = new Vec ("klíč_od_chodby_do_komnaty", true, 0);
        skrys.vlozVec(klicZamcenaChodba);
        zamcenaChodba.setKlic("klíč_od_chodby_do_komnaty");
        
        //tajnaMistnost
        Postava vzteklyPes = new Postava ("vzteklý pes", false, 12, 14);
        tajnaMistnost.vlozPostavu(vzteklyPes);
        Vec obvazy = new Vec ("obvazy", true, -10);
        vzteklyPes.vlozVec(obvazy);
        Vec skrin2 = new Vec ("skříň", false, -10);
        tajnaMistnost.vlozVec(skrin2);
        
        //prvniCela
        Postava carodejuvUcen = new Postava ("čarodějův učen", false, 18, 12);
        prvniCela.vlozPostavu(carodejuvUcen);
        Vec kbleik = new Vec ("kbelík", false, -10);
        prvniCela.vlozVec(kbleik);
        Vec postel2 = new Vec ("postel", false, -10);
        prvniCela.vlozVec(postel2);
        //rovnou sem dám i klíč
        Vec klicTajnaMistnost = new Vec ("klíč_od_tajné_místnosti", true, 0);
        prvniCela.vlozVec(klicTajnaMistnost);
        tajnaMistnost.setKlic("klíč_od_tajné_místnosti");
        
        //druhaCela
        Vec plesnivaPostel = new Vec ("plesnivá_postel", false, -10);
        druhaCela.vlozVec(plesnivaPostel);
        Vec roztrhanaBota = new Vec ("roztrhaná_bota", false, -10);
        druhaCela.vlozVec(roztrhanaBota);
        //--------------------------------------------------------------------------------------------------------
        //                  lvl4:           zamcenaChodba, kouzelnikovaKomnata
        
        //zamcenaChodba
        Postava ork = new Postava ("velký ork", false, 30, 14);
        zamcenaChodba.vlozPostavu(ork);
        Vec obvaz = new Vec ("obvaz", true, -10);
        zamcenaChodba.vlozVec(obvaz);
        Vec sirokyMec = new Vec ("široký_meč", true, 9);
        zamcenaChodba.vlozVec(sirokyMec);
        Vec socha = new Vec ("socha", false, 6);
        zamcenaChodba.vlozVec(socha);
        
        //kouzelnikovaKomnata
        Postava carodej = new Postava ("čaroděj", false, 25, 16);
        kouzelnikovaKomnata.vlozPostavu(carodej);
        Vec portal = new Vec ("portál", false, 6);
        kouzelnikovaKomnata.vlozVec(portal);
        Vec mucidlo = new Vec ("mučidlo", false, 6);
        kouzelnikovaKomnata.vlozVec(mucidlo);
        Vec stolecek = new Vec ("stoleček", false, 6);
        kouzelnikovaKomnata.vlozVec(stolecek);
        
        //--------------------------------------------------------------------------------------------------------
        aktualniProstor = znicenaVesnice;  // hra začíná ve zničené vesnici
        //konstruktor pro hlavni hrdinku
        hrdinka = new Hrdinka(hra);
    }
    
    /**
     * Generuje číslo od 0 do 100.
     * 
     * @return int generované číslo.
     */
    public static int randInt(){
        Random rand = new Random();
        return (int)rand.nextInt(100) + 1;
    }
    
    /**
     * Getter na hrdinku - hlavní postavu hry.
     * 
     * @return hrdinka.
     */
    public Hrdinka getHrdinka(){
        return hrdinka;
    }
    
    /**
     * Generuje číslo od 0 do max.
     * 
     * @return int generované číslo.
     */
    public static int randInt(int max){
        Random rand = new Random();
        return (int)rand.nextInt(max) + 1;
    }
    
       /**
     *  Metoda vrací odkaz na aktuální prostor, ve ktetém se hráč právě nachází.
     *
     *@return     aktuální prostor
     */
    public Prostor getAktualniProstor() {
        return aktualniProstor;
    }
    
    /**
     *  Metoda nastaví aktuální prostor, používá se nejčastěji při přechodu mezi prostory
     *
     *@param  prostor nový aktuální prostor
     */
    public void setAktualniProstor(Prostor prostor) {
       aktualniProstor = prostor;
    }
    
    /**
     * Getter na proměnnou naslaBratra.
     * 
     * @return boolean naslaBratra.
     */
    public boolean jeVyhra(){
        return naslaBratra;
    }

    /**
     * Nastaví hodnotu proměnné naslaBratra na true.
     */
    public void setOsvobodilaBratra(){
        naslaBratra = true;
    }
    
}
