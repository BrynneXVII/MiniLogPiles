package net.brynnexvii.minilogpiles.recipe;

import com.google.gson.JsonObject;
import net.brynnexvii.minilogpiles.MiniLogPiles;
import net.minecraft.core.NonNullList;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class MiniLogPileRecipe extends SingleItemRecipe {
    private final ResourceLocation ID;
    private final ItemStack LOG;
    private final ItemStack STRIPPED_LOG;
    private final ItemStack WOOD;
    private final ItemStack STRIPPED_WOOD;
    private final ItemStack RECIPE_ITEMS;
    protected final Ingredient ingredient;
    protected final ItemStack result;
    private final RecipeType<?> type;
    private final RecipeSerializer<?> serializer;
    protected final ResourceLocation id;
    protected final String group;

    public MiniLogPileRecipe(ResourceLocation id, ItemStack log, ItemStack strippedLog, ItemStack wood, ItemStack strippedWood, ItemStack recipeItems){
        super(this.getType(), );
        this.ID = id;
        this.LOG = log;
        this.STRIPPED_LOG = strippedLog;
        this.WOOD = wood;
        this.STRIPPED_WOOD = strippedWood;
        this.RECIPE_ITEMS = recipeItems;
    }

    @Override
    public boolean matches(SimpleContainer pContainer, Level pLevel) { // *** tbd
        return false;
    }

    @Override
    public ItemStack assemble(SimpleContainer pContainer) { //*** tbd
        return null;
    }

    @Override
    public boolean canCraftInDimensions(int pWidth, int pHeight) { //*** tbd
        return false;
    }

    @Override
    public ItemStack getResultItem() { //*** tbd
        return null;
    }

    @Override
    public ResourceLocation getId() {
        return ID;
    }

    @Override
    public RecipeSerializer<?> getSerializer() { //*** tbd
        return null;
    }

    @Override
    public RecipeType<?> getType() {
        return Type.INSTANCE;
    }

    public static class Type implements RecipeType<MiniLogPileRecipe> {
        private Type(){ }
        public static final Type INSTANCE = new Type();
        public static final String ID = "mini_log_pile";
    }

    public static class Serializer implements RecipeSerializer<MiniLogPileRecipe> {
        public static final Serializer INSTANCE = new Serializer();
        public static final ResourceLocation ID = new ResourceLocation(MiniLogPiles.MODID, "mini_log_pile");

        @Override
        public MiniLogPileRecipe fromJson(ResourceLocation pRecipeId, JsonObject pSerializedRecipe) { //*** tbd
            ItemStack log_output = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(pSerializedRecipe, "log_output"));
            ItemStack stripped_log_output = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(pSerializedRecipe, "stripped_log_output"));
            ItemStack wood_output = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(pSerializedRecipe, "wood_output"));
            ItemStack stripped_wood_output = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(pSerializedRecipe, "stripped_wood_output"));

            ItemStack input = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(pSerializedRecipe, "input"));

            return null;
        }

        @Override
        public @Nullable MiniLogPileRecipe fromNetwork(ResourceLocation pRecipeId, FriendlyByteBuf pBuffer) { //*** tbd
            return null;
        }

        @Override
        public void toNetwork(FriendlyByteBuf pBuffer, MiniLogPileRecipe pRecipe) { //*** tbd

        }
    }
}
