package theLastLightMod1.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.potions.PotionSlot;

import theLastLightMod1.DefaultMod;
import theLastLightMod1.util.TextureLoader;

import static theLastLightMod1.DefaultMod.makeRelicOutlinePath;
import static theLastLightMod1.DefaultMod.makeRelicPath;

public class BetterPotionBelt extends CustomRelic{
    /*
     * https://github.com/daviscook477/BaseMod/wiki/Custom-Relics
     *
     * Gain 2 Potion Slots.
     */

    // ID, images, text.
    public static final String ID = DefaultMod.makeID("BetterPotionBelt");

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("placeholder_relic.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("placeholder_relic.png"));

    public BetterPotionBelt() {
        super(ID, IMG, OUTLINE, RelicTier.STARTER, LandingSound.MAGICAL);
    }


    // Gain 2 Potion Slots on equip.
    @Override
    public void onEquip() {
        AbstractDungeon.player.potionSlots += 2;
        AbstractDungeon.player.potions.add(new PotionSlot(AbstractDungeon.player.potionSlots - 2));
        AbstractDungeon.player.potions.add(new PotionSlot(AbstractDungeon.player.potionSlots - 1));
    }


    // Description
    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }


}
