package blademaster.powers;

import blademaster.Blademaster;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class WindStance extends AbstractPower {
    public static final String POWER_ID = "WindStance";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public WindStance(AbstractCreature owner) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.type = PowerType.BUFF;
        this.isTurnBased = true;
        this.img = Blademaster.WindStancePNG();
        updateDescription();
    }

    public void atEndOfTurn(boolean isPlayer)
    {
        this.flash();
        if (AbstractDungeon.player.hasPower(WindCharge.POWER_ID)){
        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(AbstractDungeon.player, AbstractDungeon.player, AbstractDungeon.player.getPower(WindCharge.POWER_ID).amount * 2));
        }
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }
}