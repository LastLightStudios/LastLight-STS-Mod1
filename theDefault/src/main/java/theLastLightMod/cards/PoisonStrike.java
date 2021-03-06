package theLastLightMod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PoisonPower;
import com.megacrit.cardcrawl.powers.WeakPower;
import theLastLightMod.DefaultMod;
import theLastLightMod.characters.TheDefault;

import static theLastLightMod.DefaultMod.makeCardPath;

public class PoisonStrike extends AbstractDynamicCard{

    // TEXT DECLARATION

    public static final String ID = DefaultMod.makeID(PoisonStrike.class.getSimpleName());
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    public static final String IMG = makeCardPath("Attack.png");

    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.BASIC;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheDefault.Enums.COLOR_GRAY;

    private static final int COST = 1;
    private static final int DAMAGE = 6;
    private static final int UPGRADE_PLUS_DMG = 3;

    private static final int WEAK = 2;
    private static final int UPGRADE_PLUS_WEAK = 3;

    private static final int POISON = 4;
    private static final int UPGRADE_PLUS_POISON = 5;

    public PoisonStrike(){
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseDamage = DAMAGE;
        magicNumber = baseMagicNumber = WEAK;
        defaultSecondMagicNumber = defaultBaseSecondMagicNumber = POISON;

        this.tags.add(CardTags.STRIKE);
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
                        new PoisonPower(m, p, this.defaultSecondMagicNumber), this.defaultSecondMagicNumber));


    }

    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPGRADE_PLUS_DMG);
            upgradeMagicNumber(UPGRADE_PLUS_WEAK);
            upgradeDefaultSecondMagicNumber(UPGRADE_PLUS_POISON);
        }
    }
}
