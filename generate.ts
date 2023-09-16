// This script uses Bun.

if (typeof Bun === 'undefined') {
  console.error("'Bun' is not defined. Please run this script using the Bun runtime.");
  console.error("You can install Bun running 'npm i -g bun', or by following the directions at https://bun.sh/");
  process.exit(1);
}

const args = process.argv.slice(2);

const langFile = Bun.file(`./src/main/resources/assets/waymorewaystones/lang/en_us.json`);
const lang = await langFile.json();

const getBlockstate = (id: string) => 
  JSON.stringify({
    "variants": {
      "facing=east,half=lower": { "model": "waymorewaystones:block/" + id + "_bottom" },
      "facing=south,half=lower": { "model": "waymorewaystones:block/" + id + "_bottom", "y": 90 },
      "facing=west,half=lower": { "model": "waymorewaystones:block/" + id + "_bottom", "y": 180 },
      "facing=north,half=lower": { "model": "waymorewaystones:block/" + id + "_bottom", "y": 270 },
      "facing=east,half=upper": { "model": "waymorewaystones:block/" + id + "_top" },
      "facing=south,half=upper": { "model": "waymorewaystones:block/" + id + "_top", "y": 90 },
      "facing=west,half=upper": { "model": "waymorewaystones:block/" + id + "_top", "y": 180 },
      "facing=north,half=upper": { "model": "waymorewaystones:block/" + id + "_top", "y": 270 }
    }
  }, null, 2) + "\n";

const getBlockmodelBottom = (id: string) =>
  JSON.stringify({
    "parent": "waystones:block/waystone_bottom",
    "textures": {
      "particle": "waymorewaystones:block/" + id,
      "texture": "waymorewaystones:block/" + id,
    }
  }, null, 2) + "\n";

const getBlockmodelTop = (id: string) =>
  JSON.stringify({
    "parent": "waystones:block/waystone_top",
    "textures": {
      "particle": "waymorewaystones:block/" + id,
      "texture": "waymorewaystones:block/" + id,
    }
  }, null, 2) + "\n";

const getItemmodel = (id: string) =>
  JSON.stringify({
    "parent": "waystones:item/waystone",
    "textures": {
      "particle": "waymorewaystones:block/" + id,
      "texture": "waymorewaystones:block/" + id
    }
  }, null, 2) + "\n";

const getRecipe = (id: string) => 
  JSON.stringify({
    "result": {
      "item": "waymorewaystones:" + id
    },
    "type": "minecraft:crafting_shaped",
    "pattern": [
      " S ",
      "SWS",
      "OOO"
    ],
    "key": {
      "S": {
        "item": ""
      },
      "W": {
        "item": "waystones:warp_stone"
      },
      "O": {
        "item": "minecraft:obsidian"
      }
    }
  }, null, 2) + "\n";

const newIds = [];

for (let i = 0; i < args.length; i++) {
  const id = args[i];
  console.log(`Creating files for ${id}`);

  
  Bun.write(`./src/main/resources/assets/waymorewaystones/blockstates/${id}.json`, getBlockstate(id));
  Bun.write(`./src/main/resources/assets/waymorewaystones/models/block/${id}_bottom.json`, getBlockmodelBottom(id));
  Bun.write(`./src/main/resources/assets/waymorewaystones/models/block/${id}_top.json`, getBlockmodelTop(id));
  Bun.write(`./src/main/resources/assets/waymorewaystones/models/item/${id}.json`, getItemmodel(id));
  
  if (!await Bun.file(`./src/main/resources/assets/waymorewaystones/textures/block/${id}.png`).exists()) {
    console.log(`Need to provide textures for ${id}`);
  }
  
  if (!await Bun.file(`./src/main/resources/data/waymorewaystones/recipes/${id}.json`).exists()) {
    console.log(`Fix up recipe for ${id}`);
  }

  Bun.write(`./src/main/resources/data/waymorewaystones/recipes/${id}.json`, getRecipe(id));

  newIds.push(id);
}

if (newIds.length !== 0) {
  console.log("Updating lang file");

  for (const id of newIds) {
    if (!lang['item.waymorewaystones.' + id] || !lang['block.waymorewaystones.' + id]) {
      const name = id.split("_").map((s) => s[0].toUpperCase() + s.slice(1)).join(" ");
      console.log(`Check lang for ${id}: ${name}`);
      lang['item.waymorewaystones.' + id] = name;
      lang['block.waymorewaystones.' + id] = name;
    }
  }
  
  Bun.write(langFile, JSON.stringify(lang, null, 2) + "\n");

  console.log("Add these to WayMoreWaystones.java:\n");
  for (const id of newIds) {
    console.log(
      `public static final RegistryObject<Block> ${id.toUpperCase()} = registerBlockWithDefaultItem("${id}", () -> new WaystoneBlock(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(5f, 2000f)));`
    );
  }
}