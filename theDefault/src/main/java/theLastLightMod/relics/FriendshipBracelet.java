package theLastLightMod.relics;

import basemod.AutoAdd;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import theLastLightMod.DefaultMod;
import theLastLightMod.powers.FriendshipPower;
import theLastLightMod.util.TextureLoader;

import static theLastLightMod.DefaultMod.makeRelicOutlinePath;
import static theLastLightMod.DefaultMod.makeRelicPath;

@AutoAdd.Seen
public class FriendshipBracelet extends CustomRelic{
    /*
     * https://github.com/daviscook477/BaseMod/wiki/Custom-Relics
     *
     * At the start of each combat, gain 3 Friendship.
     */

    // ID, images, text.
    public static final String ID = DefaultMod.makeID("FriendshipBracelet");

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("placeholder_relic.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("placeholder_relic.png"));

    // Stats
    private static final int FRIENDSHIP_AMOUNT = 3;

    public FriendshipBracelet() {
        super(ID, IMG, OUTLINE, RelicTier.STARTER, LandingSound.MAGICAL);
    }

    // At the start of each combat, gain 3 Friendship.
    @Override
    public void atBattleStart() {
        flash();
        AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new FriendshipPower(AbstractDungeon.player, FRIENDSHIP_AMOUNT), FRIENDSHIP_AMOUNT));
        AbstractDungeon.actionManager.addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
    }

    // Description
    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0] + FRIENDSHIP_AMOUNT + DESCRIPTIONS[1];
    }
}
