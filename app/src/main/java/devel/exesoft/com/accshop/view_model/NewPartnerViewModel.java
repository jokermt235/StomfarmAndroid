package devel.exesoft.com.accshop.view_model;


import java.util.Observable;

import devel.exesoft.com.accshop.controller.PartnerController;
import devel.exesoft.com.accshop.model.Partner;

public class NewPartnerViewModel extends Observable {

    Partner partner;

    NewPartnerViewModel(){
        partner = new Partner();
    }

    public void onSaveClicked(){

    }

    public Partner getPartner() {
        return partner;
    }
}
