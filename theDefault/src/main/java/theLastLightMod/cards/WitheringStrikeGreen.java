package theLastLightMod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import theLastLightMod.DefaultMod;
import theLastLightMod.characters.TheDefault;
import theLastLightMod.powers.WitherPower;

import static theLastLightMod.DefaultMod.makeCardPath;

public class WitheringStrikeGreen extends AbstractDynamicCard {

    // TEXT DECLARATION

    public static final String ID = DefaultMod.makeID(WitheringStrikeGreen.class.getSimpleName());
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    public static final String IMG = makeCardPath("Attack.png");

    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = AbstractCard.CardColor.GREEN;

    private static final int COST = 1;
    private static final int DAMAGE = 6;
    private static final int UPGRADE_PLUS_DMG = 2;

    private static final int WEAK = 1;
    //private static final int UPGRADE_PLUS_WEAK = 1;

    private static final int WITHER = 2;
    private static final int UPGRADE_PLUS_WITHER = 2;

    public WitheringStrikeGreen() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseDamage = DAMAGE;
        magicNumber = baseMagicNumber = WEAK;
        defaultSecondMagicNumber = defaultBaseSecondMagicNumber = WITHER;

    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(
                new DamageAction(m,
                        new DamageInfo(p, this.damage, this.damageTypeForTurn),
                        AbstractGameAction.AttackEffect.SLASH_VERTICAL));
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
            upgradeDamage(UPGRADE_PLUS_DMG);
            //upgradeMagicNumber(UPGRADE_PLUS_WEAK);
            upgradeDefaultSecondMagicNumber(UPGRADE_PLUS_WITHER);
        }
    }
}
