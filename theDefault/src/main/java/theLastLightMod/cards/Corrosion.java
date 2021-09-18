package theLastLightMod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theLastLightMod.DefaultMod;
import theLastLightMod.powers.CorrosionPower;

import static theLastLightMod.DefaultMod.makeCardPath;

public class Corrosion extends AbstractDynamicCard{

    // TEXT DECLARATION

    public static final String ID = DefaultMod.makeID(Corrosion.class.getSimpleName());
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    public static final String IMG = makeCardPath("Attack.png");

    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.POWER;
    public static final CardColor COLOR = CardColor.GREEN;

    private static final int COST = 1;
    private static final int UPGRADED_COST = 0;

    private static final int STACKS_ADDED = 1;
    private static final int STACKS_ADDED_PLUS_UPGRADE = 0;

    public Corrosion() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        this.baseMagicNumber = STACKS_ADDED;
        this.magicNumber = this.baseMagicNumber;
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(
                new ApplyPowerAction(p, p,
                        new CorrosionPower(p, this.magicNumber), this.magicNumber));
    }

    @Override
    public void upgrade(){
        if (!upgraded) {
            upgradeBaseCost(UPGRADED_COST);
            upgradeName();
            initializeDescription();
        }
    }
}
