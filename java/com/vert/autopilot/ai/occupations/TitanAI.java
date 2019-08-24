package com.vert.autopilot.ai.occupations;

import com.l2jmobius.gameserver.enums.ShotType;
import com.l2jmobius.gameserver.model.skills.Skill;
import com.vert.autopilot.FakePlayer;
import com.vert.autopilot.ai.CombatAI;
import com.vert.autopilot.helpers.FakeHelpers;
import com.vert.autopilot.models.HealingSpell;
import com.vert.autopilot.models.OffensiveSpell;
import com.vert.autopilot.models.SpellUsageCondition;
import com.vert.autopilot.models.SupportSpell;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author vert
 */
public class TitanAI extends CombatAI {
    public TitanAI(FakePlayer character)
    {
        super(character);
    }

    @Override
    public void thinkAndAct()
    {
        super.thinkAndAct();
        setBusyThinking(true);
        applyDefaultBuffs();
        handleShots();
        selfSupportBuffs();
        tryTargetRandomCreatureByTypeInRadius(FakeHelpers.getTestTargetRange());
        tryAttackingUsingFighterOffensiveSkill();
        setBusyThinking(false);
    }

    @Override
    protected double chanceOfUsingSkill() {
        return 0.10;
    }

    @Override
    protected ShotType getShotType()
    {
        return ShotType.SOULSHOTS;
    }

    @Override
    protected List<OffensiveSpell> getOffensiveSpells()
    {
        List<OffensiveSpell> _offensiveSpells = new ArrayList<>();
        _offensiveSpells.add(new OffensiveSpell(315, 1));
        _offensiveSpells.add(new OffensiveSpell(190, 2));
        _offensiveSpells.add(new OffensiveSpell(362, 3));
        return _offensiveSpells;
    }

    @Override
    public List<SupportSpell> getSelfSupportSpells()
    {
        List<SupportSpell> _selfSupportSpells = new ArrayList<>();
        _selfSupportSpells.add(new SupportSpell(139, SpellUsageCondition.LESSHPPERCENT, 30, 1));
        _selfSupportSpells.add(new SupportSpell(176, SpellUsageCondition.LESSHPPERCENT, 30, 2));
        return _selfSupportSpells;
    }

    @Override
    protected int[][] getBuffs()
    {
        return FakeHelpers.getFighterBuffs();
    }

    @Override
    protected List<HealingSpell> getHealingSpells()
    {
        return Collections.emptyList();
    }

    @Override
    protected boolean classOffensiveSkillsId(Skill skill) {
        return false;
    }
}