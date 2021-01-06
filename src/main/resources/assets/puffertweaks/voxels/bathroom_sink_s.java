Stream.of(
        Block.makeCuboidShape(4,0,4,12,2,12),
        Block.makeCuboidShape(10,5,3,11,6,4),
        Block.makeCuboidShape(12,2,2,13,5,13),
        Block.makeCuboidShape(3,2,2,4,5,13),
        Block.makeCuboidShape(4,2,12,12,5,13),
        Block.makeCuboidShape(4,2,2,12,5,4),
        Block.makeCuboidShape(7,5,2,9,8,3),
        Block.makeCuboidShape(7,8,2,9,9,6),
        Block.makeCuboidShape(7,7.699999999999999,5,9,8,6),
        Block.makeCuboidShape(5,5,3,6,6,4),
        Block.makeCuboidShape(5,1,3,11,2,4)
        ).reduce((v1,v2)->{return VoxelShapes.combineAndSimplify(v1,v2,IBooleanFunction.OR);});