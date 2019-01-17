package me.dehys.TaxesPlugin;

public class Config {
    Hook hook;

    public Config(Hook hook){
        this.hook = hook;
    }

    private void save(){
        hook.saveConfig();
    }

    public int delay(){ return hook.getConfig().getInt("delay");}
    public void setDelay(int i){ hook.getConfig().set("delay", i); save();}

    public long updateRatio(){ return hook.getConfig().getLong("updateRatio");}
    public void setUpdateRatio(long l){ hook.getConfig().set("updateRatio", l); save();}

    public int taxRate(){ return hook.getConfig().getInt("taxRate")/100;}
    public void setTaxRate(int i){ hook.getConfig().set("taxRate", i); save();}

    public boolean taxAmountEnabled(){ return hook.getConfig().getBoolean("taxAmountEnabled");}
    public void setTaxAmountEnabled(boolean b){ hook.getConfig().set("taxAmountEnabled", b); save();}

    public double taxAmount(){ return hook.getConfig().getDouble("taxAmount");}
    public void setTaxAmount(double d){ hook.getConfig().set("taxAmount", d); save();}


    public int currentState(){ return hook.getConfig().getInt("currentState");}
    public void setCurrentState(int i){ hook.getConfig().set("currentState", i); save();}

    public int taxTimes(){ return hook.getConfig().getInt("taxTimes");}
    public void setTaxTimes(int i){ hook.getConfig().set("taxTimes", i); save();}
}
