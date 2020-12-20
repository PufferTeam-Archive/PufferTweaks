Stream.of(
Block.makeCuboidShape(4, 0, 4, 12, 2, 12),
Block.makeCuboidShape(5, 5, 12, 6, 6, 13),
Block.makeCuboidShape(3, 2, 3, 4, 5, 14),
Block.makeCuboidShape(12, 2, 3, 13, 5, 14),
Block.makeCuboidShape(4, 2, 3, 12, 5, 4),
Block.makeCuboidShape(4, 2, 12, 12, 5, 14),
Block.makeCuboidShape(7, 5, 13, 9, 8, 14),
Block.makeCuboidShape(7, 8, 10, 9, 9, 14),
Block.makeCuboidShape(7, 7.699999999999999, 10, 9, 8, 11),
Block.makeCuboidShape(10, 5, 12, 11, 6, 13),
Block.makeCuboidShape(5, 1, 12, 11, 2, 13)
).reduce((v1, v2) -> {return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);});