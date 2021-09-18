package theLastLightMod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theLastLightMod.powers.WitherPower;

public class TriggerWitherDamageAction extends AbstractGameAction {

    public TriggerWitherDamageAction(AbstractCreature target){
        this.target = target;
    }

    @Override
    public void update(){
        for (AbstractPower p : this.target.powers){
            if (p instanceof WitherPower){
                ((WitherPower) p).triggerWitherDamage();
            }
        }
        this.isDone = true;
    }
}

