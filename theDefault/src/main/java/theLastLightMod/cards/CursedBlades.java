package theLastLightMod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.tempCards.Shiv;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theLastLightMod.DefaultMod;
import theLastLightMod.powers.CursedBladesPower;
import theLastLightMod.powers.WitherPower;

import static theLastLightMod.DefaultMod.makeCardPath;

public class CursedBlades extends AbstractDynamicCard{

    // TEXT DECLARATION

    public static final String ID = DefaultMod.makeID(CursedBlades.class.getSimpleName());
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    public static final String IMG = makeCardPath("Attack.png");

    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = CardColor.GREEN;

    private static final int COST = 1;

    private static final int SHIVS = 2;
    private static final int UPGRADE_PLUS_SHIVS = 1;

    // used to apply Wither to all enemies
    private static final int WITHER = 2;
    private static final int UPGRADE_PLUS_WITHER = 1;

    private static final int WITHER_ON_HIT = 1;
    private static final int UPGRADE_PLUS_WITHER_ON_HIT = 1;

    public CursedBlades() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        magicNumber = baseMagicNumber = SHIVS;
        defaultSecondMagicNumber = defaultBaseSecondMagicNumber = WITHER_ON_HIT;
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()){
            flash();
            addToBot(new MakeTempCardInHandAction(new Shiv(), this.magicNumber));
        }
        addToBot(
                new ApplyPowerAction(p, p,
                        new CursedBladesPower(p, this.defaultSecondMagicNumber), this.defaultBaseSecondMagicNumber));
    }

    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(UPGRADE_PLUS_SHIVS);
            upgradeDefaultSecondMagicNumber(UPGRADE_PLUS_WITHER_ON_HIT);
        }
    }
}
