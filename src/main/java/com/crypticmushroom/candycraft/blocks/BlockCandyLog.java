package com.crypticmushroom.candycraft.blocks;

import net.minecraft.block.BlockLog;
import net.minecraft.block.SoundType;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class BlockCandyLog extends BlockLog {
    public static final PropertyEnum properties = PropertyEnum.create("metadata", BlockCandyLog.EnumType.class);

    public BlockCandyLog() {
        setDefaultState(blockState.getBaseState().withProperty(properties, BlockCandyLog.EnumType.TYPE0).withProperty(BlockLog.LOG_AXIS, BlockLog.EnumAxis.Y));
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        IBlockState iblockstate = getDefaultState().withProperty(properties, BlockCandyLog.EnumType.func_176837_a((meta & 3) % 4));

        switch (meta & 12) {
            case 0:
                iblockstate = iblockstate.withProperty(LOG_AXIS, BlockLog.EnumAxis.Y);
                break;
            case 4:
                iblockstate = iblockstate.withProperty(LOG_AXIS, BlockLog.EnumAxis.X);
                break;
            case 8:
                iblockstate = iblockstate.withProperty(LOG_AXIS, BlockLog.EnumAxis.Z);
                break;
            default:
                iblockstate = iblockstate.withProperty(LOG_AXIS, BlockLog.EnumAxis.NONE);
        }

        return iblockstate;
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        byte b0 = 0;
        int i = b0 | ((BlockCandyLog.EnumType) state.getValue(properties)).getMeta();

        switch (BlockCandyLog.SwitchEnumAxis.field_180203_a[state.getValue(LOG_AXIS).ordinal()]) {
            case 1:
                i |= 4;
                break;
            case 2:
                i |= 8;
                break;
            case 3:
                i |= 12;
        }

        return i;
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, new IProperty[]{properties, LOG_AXIS});
    }


    @Override
    public int damageDropped(IBlockState state) {
        return ((BlockCandyLog.EnumType) state.getValue(properties)).getMeta();
    }

    @Override
    public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items) {
        items.add(new ItemStack(this, 1, 0));
        items.add(new ItemStack(this, 1, 1));
        items.add(new ItemStack(this, 1, 2));
    }

    @Override
    public SoundType getSoundType(IBlockState state, World world, BlockPos pos, @Nullable Entity entity) {
        return SoundType.WOOD;
    }

    public enum EnumType implements IStringSerializable {
        TYPE0(0, "0"), TYPE1(1, "1"), TYPE2(2, "2");

        private static final BlockCandyLog.EnumType[] enumList = new BlockCandyLog.EnumType[values().length];

        static {
            BlockCandyLog.EnumType[] var0 = values();
            int var1 = var0.length;

            for (int var2 = 0; var2 < var1; ++var2) {
                BlockCandyLog.EnumType var3 = var0[var2];
                enumList[var3.getMeta()] = var3;
            }
        }

        private final int meta;
        private final String name;

        private EnumType(int m, String n) {
            meta = m;
            name = n;
        }

        public static BlockCandyLog.EnumType getState(int meta) {
            if (meta < 0 || meta >= enumList.length) {
                meta = 0;
            }

            return enumList[meta];
        }

        public static BlockCandyLog.EnumType func_176837_a(int p_176837_0_) {
            if (p_176837_0_ < 0 || p_176837_0_ >= enumList.length) {
                p_176837_0_ = 0;
            }

            return enumList[p_176837_0_];
        }

        public int getMeta() {
            return meta;
        }

        @Override
        public String toString() {
            return name;
        }

        @Override
        public String getName() {
            return name;
        }
    }

    static final class SwitchEnumAxis {
        static final int[] field_180203_a = new int[BlockLog.EnumAxis.values().length];

        static {
            try {
                field_180203_a[BlockLog.EnumAxis.X.ordinal()] = 1;
            } catch (NoSuchFieldError var3) {
                ;
            }

            try {
                field_180203_a[BlockLog.EnumAxis.Z.ordinal()] = 2;
            } catch (NoSuchFieldError var2) {
                ;
            }

            try {
                field_180203_a[BlockLog.EnumAxis.NONE.ordinal()] = 3;
            } catch (NoSuchFieldError var1) {
                ;
            }
        }
    }
}
