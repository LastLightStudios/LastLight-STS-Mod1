package theLastLightMod.patches;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.actions.unique.PoisonLoseHpAction;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;

import theLastLightMod.powers.WitherPower;

import javassist.CtBehavior;



//@SpirePatch(clz = PoisonLoseHpAction.class, method = "update")
public class WitherPowerPatches {
    @SpirePatch(clz = WeakPower.class, method = "atEndOfRound")
    public static class WitherWeakInteraction{
        @SpireInsertPatch(locator = WeakLocator.class)
        public static SpireReturn<Void> WeakPatchMethod(WeakPower __instance){
            for (AbstractPower p : __instance.owner.powers) {
                if (p.ID.equals(WitherPower.POWER_ID))
                    return SpireReturn.Return();
            }
            return SpireReturn.Continue();
        }

        private static class WeakLocator extends SpireInsertLocator {
            @Override
            public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
                Matcher finalMatcher = new Matcher.FieldAccessMatcher(WeakPower.class, "amount");
                return LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
            }
        }
    }

    @SpirePatch(clz = VulnerablePower.class, method = "atEndOfRound")
    public static class WitherVulnerableInteraction{
        @SpireInsertPatch(locator = VulnerableLocator.class)
        public static SpireReturn<Void> VulnerablePatchMethod(VulnerablePower __instance){
            for (AbstractPower p : __instance.owner.powers) {
                if (p.ID.equals(WitherPower.POWER_ID))
                    return SpireReturn.Return();
            }
            return SpireReturn.Continue();
        }

        private static class VulnerableLocator extends SpireInsertLocator {
            @Override
            public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
                Matcher finalMatcher = new Matcher.FieldAccessMatcher(VulnerablePower.class, "amount");
                return LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
            }
        }
    }


/*
    private static class PoisonLocator extends SpireInsertLocator {
        @Override
        public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
            Matcher finalMatcher = new Matcher.FieldAccessMatcher(PoisonLoseHpAction.class, "amount");
            return LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
        }
    }
 */
}
