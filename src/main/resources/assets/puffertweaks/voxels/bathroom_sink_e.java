Stream.of(
Block.makeCuboidShape(4, 0, 4, 12, 2, 12),
Block.makeCuboidShape(3, 5, 5, 4, 6, 6),
Block.makeCuboidShape(2, 2, 3, 13, 5, 4),
Block.makeCuboidShape(2, 2, 12, 13, 5, 13),
Block.makeCuboidShape(12, 2, 4, 13, 5, 12),
Block.makeCuboidShape(2, 2, 4, 4, 5, 12),
Block.makeCuboidShape(2, 5, 7, 3, 8, 9),
Block.makeCuboidShape(2, 8, 7, 6, 9, 9),
Block.makeCuboidShape(5, 7.699999999999999, 7, 6, 8, 9),
Block.makeCuboidShape(3, 5, 10, 4, 6, 11),
Block.makeCuboidShape(3, 1, 5, 4, 2, 11)
).reduce((v1, v2) -> {return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);});