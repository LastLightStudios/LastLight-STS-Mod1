package theLastLightMod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import theLastLightMod.DefaultMod;
import theLastLightMod.characters.TheDefault;
import theLastLightMod.powers.WitherPower;

import static theLastLightMod.DefaultMod.makeCardPath;

public class WitheringGlare extends AbstractDynamicCard{

    // TEXT DECLARATION

    public static final String ID = DefaultMod.makeID(WitheringStrike.class.getSimpleName());
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    public static final String IMG = makeCardPath("Attack.png");

    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheDefault.Enums.COLOR_GRAY;

    private static final int COST = 1;

    private static final int POISON = 2;
    private static final int UPGRADE_PLUS_POISON = 3;

    private static final int WITHER = 4;
    private static final int UPGRADE_PLUS_WITHER = 5;

    public WitheringGlare() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        magicNumber = baseMagicNumber = POISON;
        defaultSecondMagicNumber = defaultBaseSecondMagicNumber = WITHER;
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(
                new ApplyPowerAction(m, p,
                        new WeakPower(m, this.magicNumber, false), this.magicNumber));
        addToBot(
                new ApplyPowerAction(m, p,
                        new WitherPower(m, this.defaultSecondMagicNumber, false), this.defaultSecondMagicNumber));
    }

    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(UPGRADE_PLUS_POISON);
            upgradeDefaultSecondMagicNumber(UPGRADE_PLUS_WITHER);
        }
    }
}
