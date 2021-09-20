package theLastLightMod.powers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theLastLightMod.DefaultMod;
import theLastLightMod.util.TextureLoader;

import static theLastLightMod.DefaultMod.makePowerPath;

public class CursedBladesPower extends AbstractPower {

    public static final String POWER_ID = DefaultMod.makeID("CursedBladesPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    // We create 2 new textures *Using This Specific Texture Loader* - an 84x84 image and a 32x32 one.
    // There's a fallback "missing texture" image, so the game shouldn't crash if you accidentally put a non-existent file.
    private static final Texture tex84 = TextureLoader.getTexture(makePowerPath("placeholder_power84.png"));
    private static final Texture tex32 = TextureLoader.getTexture(makePowerPath("placeholder_power32.png"));

    public CursedBladesPower(final AbstractCreature owner, int newAmount) {
        name = NAME;
        ID = POWER_ID;

        this.owner = owner;
        this.amount = newAmount;

        updateDescription();
        // We load those textures here.
        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);
        type = PowerType.BUFF;
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }

    @Override
    public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target){
        if (target != this.owner && info.type == DamageInfo.DamageType.NORMAL){
            flash();
            addToTop(
                    new ApplyPowerAction(target, this.owner,
                            new WitherPower(target, this.amount, false), this.amount, true));
        }
    }

    @Override
    public void atEndOfRound(){
        addToBot(
                new RemoveSpecificPowerAction(this.owner, this.owner, this.ID)
        );
    }
}
