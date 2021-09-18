package theLastLightMod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import theLastLightMod.DefaultMod;
import theLastLightMod.powers.SappingWindsPower;
import theLastLightMod.powers.WitherPower;

import static theLastLightMod.DefaultMod.makeCardPath;

public class SappingWinds extends AbstractDynamicCard{

    // TEXT DECLARATION

    public static final String ID = DefaultMod.makeID(SappingWinds.class.getSimpleName());
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    public static final String IMG = makeCardPath("Attack.png");

    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.POWER;
    public static final CardColor COLOR = AbstractCard.CardColor.GREEN;

    private static final int COST = 1;

    private static final int WITHER = 2;
    private static final int UPGRADE_PLUS_WITHER = 1;

    public SappingWinds() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        magicNumber = baseMagicNumber = WITHER;
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(
                new ApplyPowerAction(p, p,
                        new SappingWindsPower(p, this.magicNumber)));
    }

    @Override
    public void upgrade(){
        if (!upgraded) {
            upgradeMagicNumber(UPGRADE_PLUS_WITHER);
            upgradeName();
            initializeDescription();
        }
    }
}
