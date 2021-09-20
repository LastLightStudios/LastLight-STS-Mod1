package theLastLightMod.cards;

import basemod.AutoAdd;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PoisonPower;
import theLastLightMod.DefaultMod;
import theLastLightMod.powers.WitherPower;

import static theLastLightMod.DefaultMod.makeCardPath;

@AutoAdd.Ignore
public class WitheringGlareGreen extends AbstractDynamicCard{

    // TEXT DECLARATION

    public static final String ID = DefaultMod.makeID(WitheringGlareGreen.class.getSimpleName());
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    public static final String IMG = makeCardPath("Attack.png");

    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = AbstractCard.CardColor.GREEN;

    private static final int COST = 1;

    private static final int POISON = 3;
    private static final int UPGRADE_PLUS_POISON = 2;

    private static final int WITHER = 3;
    private static final int UPGRADE_PLUS_WITHER = 2;

    public WitheringGlareGreen() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        magicNumber = baseMagicNumber = POISON;
        defaultSecondMagicNumber = defaultBaseSecondMagicNumber = WITHER;
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(
                new ApplyPowerAction(m, p,
                        new PoisonPower(m, p, this.magicNumber), this.magicNumber));
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
