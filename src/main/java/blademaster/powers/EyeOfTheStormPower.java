package blademaster.powers;

import blademaster.Blademaster;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class EyeOfTheStormPower extends AbstractPower {
    public static final String POWER_ID = "EyeOfTheStorm";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public EyeOfTheStormPower(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.type = PowerType.BUFF;
        this.isTurnBased = true;
        this.img = Blademaster.getDefaultPowerTexture();
    }

    public int onAttacked(DamageInfo info, int damageAmount) {
        this.flash();
        if ((damageAmount > 0) && (info.owner != null) && (info.type == DamageInfo.DamageType.NORMAL) && (info.type != DamageInfo.DamageType.HP_LOSS))
        {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new LightningCharge(AbstractDungeon.player, this.amount), this.amount));
        }
        if (damageAmount < this.amount){
            return 0;
        } else {return (damageAmount - this.amount);}
    }


    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2];
    }
}