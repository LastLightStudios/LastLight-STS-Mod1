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
            if (__instance.owner.hasPower(WitherPower.POWER_ID)){
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
            if (__instance.owner.hasPower(WitherPower.POWER_ID)){
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

    @SpirePatch(clz = PoisonLoseHpAction.class, method = "update")
    public static class WitherPoisonInteraction{
        @SpireInsertPatch(locator = PoisonLocator.class, localvars = {"p"})
        public static void PoisonPatchMethod(PoisonLoseHpAction __instance, AbstractPower p){
            if (p.owner.hasPower(WitherPower.POWER_ID))
            {
                p.amount++;
                return;
            }
            return;
        }

        private static class PoisonLocator extends SpireInsertLocator {
            @Override
            public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
                Matcher finalMatcher = new Matcher.FieldAccessMatcher(AbstractPower.class, "amount");
                return new int[] {LineFinder.findAllInOrder(ctMethodToPatch, finalMatcher)[1]};
            }
        }
    }
}
