package fr.minemobs.puffertweaks.object.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.CauldronBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class AcaciaCauldron extends CauldronBlock {
    public AcaciaCauldron(Properties properties) {
        super(properties);
    }

    @Override
    public void fillWithRain(World worldIn, BlockPos pos) {
        if (worldIn.rand.nextInt(10) % 2 == 0) {
            float f = worldIn.getBiome(pos).getTemperature(pos);
            if (!(f < 0.15F)) {
                BlockState blockstate = worldIn.getBlockState(pos);
                if (blockstate.get(LEVEL) < 3) {
                    worldIn.setBlockState(pos, blockstate.func_235896_a_(LEVEL), 2);
                }
                System.out.println("Salut");
            }else{
                System.out.println(f);
            }
        }
    }
}
