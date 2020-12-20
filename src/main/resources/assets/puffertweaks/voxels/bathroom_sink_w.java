Stream.of(
Block.makeCuboidShape(4, 0, 4, 12, 2, 12),
Block.makeCuboidShape(12, 5, 10, 13, 6, 11),
Block.makeCuboidShape(3, 2, 12, 14, 5, 13),
Block.makeCuboidShape(3, 2, 3, 14, 5, 4),
Block.makeCuboidShape(3, 2, 4, 4, 5, 12),
Block.makeCuboidShape(12, 2, 4, 14, 5, 12),
Block.makeCuboidShape(13, 5, 7, 14, 8, 9),
Block.makeCuboidShape(10, 8, 7, 14, 9, 9),
Block.makeCuboidShape(10, 7.699999999999999, 7, 11, 8, 9),
Block.makeCuboidShape(12, 5, 5, 13, 6, 6),
Block.makeCuboidShape(12, 1, 5, 13, 2, 11)
).reduce((v1, v2) -> {return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);});