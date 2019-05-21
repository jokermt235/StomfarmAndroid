package devel.exesoft.com.accshop.view_model;


import java.util.Observable;

import devel.exesoft.com.accshop.controller.PartnerController;
import devel.exesoft.com.accshop.model.Partner;

public class NewPartnerViewModel extends Observable {

    Partner partner;

    public NewPartnerViewModel(){
        partner = new Partner();
        partner.setStatus("new");
    }
    public void onSaveClicked(){
        PartnerController.add(getPartner());
    }
    public Partner getPartner() {
        return partner;
    }
}
