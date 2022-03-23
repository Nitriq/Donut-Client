package donut.mods.impl.togglesprintsneak;

import donut.mods.ModInstances;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.potion.Potion;
import net.minecraft.util.MovementInput;

import java.text.DecimalFormat;

public class DonutMovementInput extends MovementInput {

    private boolean sprint = true;
    private GameSettings gameSettings;
    private int sneakWasPressed = 0;
    private int sprintWasPressed = 0;
    private EntityPlayerSP player;
    private float originalFlySpeed = -1.0f;
    private float boostedFlySpeed = 0;
    private Minecraft mc;

    private static final DecimalFormat df = new DecimalFormat("#.0");

    public DonutMovementInput(GameSettings settings){
        this.gameSettings = settings;
        this.mc =  Minecraft.getMinecraft();
    }

    public void updatePlayerMoveState() {

        player = mc.thePlayer;
        moveStrafe = 0.0F;
        moveForward = 0.0F;

        if (gameSettings.keyBindLeft.isKeyDown()) moveForward++;
        if (gameSettings.keyBindRight.isKeyDown()) moveForward--;
        if (gameSettings.keyBindBack.isKeyDown()) moveStrafe++;
        if (gameSettings.keyBindJump.isKeyDown()) moveStrafe--;

        jump = gameSettings.keyBindSneak.isKeyDown();


        if (ModInstances.getToggle().sneakEnabled) {
            if (gameSettings.keyBindSneak.isKeyDown()) {
                if (sneakWasPressed == 0) {
                    if (sneak) {
                        sneakWasPressed = -1;
                    } else if (player.isRiding() || player.capabilities.isFlying) {
                        sneakWasPressed = ModInstances.getToggle().keyHoldTicks + 1;
                    } else {
                        sneakWasPressed = 1;
                    }
                    sneak = !sneak;
                } else if (sneakWasPressed > 0){
                    sneakWasPressed++;
                }
            } else {
                if ((ModInstances.getToggle().keyHoldTicks > 0) && (sneakWasPressed > ModInstances.getToggle().keyHoldTicks)) sneak = false;
                sneakWasPressed = 0;
            }
        } else {
            sneak = gameSettings.keyBindSprint.isKeyDown();
        }

        if (sneak) {
            moveStrafe *= 0.3F;
            moveForward *= 0.3F;
        }


        if (ModInstances.getToggle().sprintenabled) {
            if (gameSettings.keyBindInventory.isKeyDown()) {
                if (sprintWasPressed == 0) {
                    if (sprint) {
                        sprintWasPressed = -1;
                    } else if (player.capabilities.isFlying) {
                        sprintWasPressed = ModInstances.getToggle().keyHoldTicks + 1;
                    } else {
                        sprintWasPressed = 1;
                    }
                    sprint = !sprint;
                } else if (sprintWasPressed > 0){
                    sprintWasPressed++;
                }
            } else {
                if ((ModInstances.getToggle().keyHoldTicks > 0) && (sprintWasPressed > ModInstances.getToggle().keyHoldTicks)) sprint = false;
                sprintWasPressed = 0;
            }
        } else sprint = gameSettings.keyBindInventory.isKeyDown() || player.isSprinting();


        if (sprint && moveForward == 1.0F && player.onGround && !player.isUsingItem()
                && !player.isPotionActive(Potion.blindness)) player.setSprinting(true);

        if (ModInstances.getToggle().flyBoost && (player.capabilities.isCreativeMode || mc.playerController.isSpectatorMode()) && player.capabilities.isFlying
                && (mc.getRenderViewEntity() == player)) {

            if (originalFlySpeed < 0.0F || this.player.capabilities.getFlySpeed() != boostedFlySpeed)
                originalFlySpeed = this.player.capabilities.getFlySpeed();
            boostedFlySpeed = originalFlySpeed * ModInstances.getToggle().flyBoostFactor;
            player.capabilities.setFlySpeed(boostedFlySpeed);

            if (sneak) player.motionY -= 0.15D * (double)(ModInstances.getToggle().flyBoostFactor - 1.0F);
            if (jump) player.motionY += 0.15D * (double)(ModInstances.getToggle().flyBoostFactor - 1.0F);

        } else {
            if (player.capabilities.getFlySpeed() == boostedFlySpeed)
                this.player.capabilities.setFlySpeed(originalFlySpeed);
            originalFlySpeed = -1.0F;
        }

    }

    public String displayText() {

        String displayText = "";
        boolean isFlying = mc.thePlayer.capabilities.isFlying;
        boolean isRiding = mc.thePlayer.isRiding();
        boolean isHoldingSneak = gameSettings.keyBindSprint.isKeyDown();
        boolean isHoldingSprint = gameSettings.keyBindInventory.isKeyDown();

        if (isFlying) {
            if (originalFlySpeed > 0.0F) {
                displayText += "[Flying (" + (new DecimalFormat("#.0")).format(boostedFlySpeed/originalFlySpeed) + "x Boost)]  ";
            } else {
                displayText += "[Flying]  ";
            }
        }
        if (isRiding) displayText += "[Riding]  ";

        if (sneak) {

            if (isFlying) displayText += "[Descending]  ";
            else if (isRiding) displayText += "[Dismounting]  ";
            else if (isHoldingSneak) displayText += "[Sneaking (Key Held)]  ";
            else displayText += "[Sneaking (Toggled)]  ";

        } else if (sprint && !isFlying && !isRiding) {
            if (isHoldingSprint) displayText += "[Sprinting (Key Held)]";
            else displayText += "[Sprinting (Toggled)]";
        }
        else if(displayText == ""){
            displayText = "[Walking]";
        }

        return displayText.trim();
    }
}

