package ages.content;

import arc.struct.Seq;
import ages.util.IndObjectives;
import mindustry.content.Items;
import mindustry.content.Liquids;
import mindustry.content.TechTree;
import mindustry.ctype.UnlockableContent;
import mindustry.game.Objectives;
import mindustry.type.ItemStack;

import static mindustry.content.Blocks.*;
import static mindustry.content.Items.*;
import static mindustry.content.SectorPresets.*;
import static ages.content.AgesItems.*;
import static ages.content.AgesFocus.*;

public class AgesTechTree{
    static TechTree.TechNode context = null;

    public static void load(){
        mergeNode(coreShard, () -> {
            /*
            node(defEffort, Seq.with(
                    new Objectives.SectorComplete(groundZero)
            ), () -> {
                node(wallFirst, Seq.with(
                        new Objectives.Research(copperWall),
                        new Objectives.Research(copperWallLarge),
                        new Objectives.SectorComplete(groundZero),
                        new IndObjectives.sectorsCompleted(3),
                        new IndObjectives.notUnlocked(turretFirst)
                ), () -> {});

                node(turretFirst, Seq.with(
                        new Objectives.Research(duo),
                        new Objectives.Research(scatter),
                        new Objectives.SectorComplete(groundZero),
                        new IndObjectives.sectorsCompleted(3),
                        new IndObjectives.notUnlocked(wallFirst)
                ), () -> {});
            });


            node(resI, Seq.with(
                    new Objectives.SectorComplete(groundZero),
                    new Objectives.Research(copper),
                    new Objectives.Research(lead),
                    new IndObjectives.sectorsCompleted(3)
            ), () -> {
                node(oreI, Seq.with(
                        new IndObjectives.sectorsCompleted(5)
                ), () -> {
                    node(oreII, Seq.with(
                            new IndObjectives.sectorsCompleted(5),
                            new Objectives.Research(coal),
                            new Objectives.Research(Items.sand),
                            new Objectives.Research(scrap)
                    ), () -> {
                        node(oreIII, Seq.with(
                                new IndObjectives.sectorsCompleted(15),
                                new Objectives.Research(titanium),
                                new Objectives.Research(thorium)
                        ), () -> {

                        });
                    });
                });

                node(liquidI, Seq.with(
                        new IndObjectives.sectorsCompleted(15),
                        new Objectives.Research(Liquids.water),
                        new Objectives.Research(sporePod),
                        new IndObjectives.notUnlocked(liquidTank)
                ), () -> {
                });

                node(liquidII, Seq.with(
                        new IndObjectives.sectorsCompleted(15),
                        new Objectives.Research(Liquids.oil),
                        new Objectives.Research(plastanium)
                ), () -> {

                });

                node(resII, Seq.with(
                        new IndObjectives.sectorsCompleted(5),
                        new Objectives.Research(graphite),
                        new Objectives.Research(metaglass)
                ), () -> {
                    node(resIII, Seq.with(
                            new IndObjectives.sectorsCompleted(10),
                            new Objectives.Research(silicon)
                    ), () -> {
                        node(resIV, Seq.with(
                                new IndObjectives.sectorsCompleted(15),
                                new Objectives.Research(plastanium),
                                new Objectives.Research(phaseFabric)
                        ), () -> {
                            node(resV, Seq.with(
                                    new IndObjectives.sectorsCompleted(20),
                                    new Objectives.Research(surgeAlloy)
                            ), () -> {});
                        });
                    });
                });
            });
            */


        });
    }

    private static void mergeNode(UnlockableContent parent, Runnable children){
        context = TechTree.all.find(t -> t.content == parent);
        children.run();
    }

    private static void node(UnlockableContent content, ItemStack[] requirements, Seq<Objectives.Objective> objectives, Runnable children){
        TechTree.TechNode node = new TechTree.TechNode(context, content, requirements);
        if(objectives != null) node.objectives = objectives;

        TechTree.TechNode prev = context;
        context = node;
        children.run();
        context = prev;
    }

    private static void node(UnlockableContent content, ItemStack[] requirements, Runnable children){
        node(content, requirements, null, children);
    }

    private static void node(UnlockableContent content, Seq<Objectives.Objective> objectives, Runnable children){
        node(content, content.researchRequirements(), objectives, children);
    }

    private static void node(UnlockableContent content, Runnable children){
        node(content, content.researchRequirements(), children);
    }

    private static void node(UnlockableContent block){
        node(block, () -> {});
    }

    private static void nodeProduce(UnlockableContent content, Seq<Objectives.Objective> objectives, Runnable children){
        node(content, content.researchRequirements(), objectives.add(new Objectives.Produce(content)), children);
    }

    private static void nodeProduce(UnlockableContent content, Runnable children){
        nodeProduce(content, Seq.with(), children);
    }

    private static void nodeProduce(UnlockableContent content){
        nodeProduce(content, Seq.with(), () -> {});
    }
}

