package com.example.rpg.Calc.map;

import java.io.Serializable;
import static com.example.rpg.Calc.map.cave.Cave1.CV1;
import static com.example.rpg.Calc.map.Map.*;
import static com.example.rpg.Calc.map.PersonHome1.*;
import static com.example.rpg.graphic.GameActivity.game_activity;
import android.widget.ImageView;

import com.example.rpg.graphic.GameActivity;

public class World_map implements Serializable {
    public static String[][] calc_world_map = {
            {E, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E},
            {E, G, G, G, G, G, G, G, G, G, G, G, G, G, G, G, G, G, G, G, G, G, E},
            {E, G, G, G, G, G, G, G, G, G, G, G, G, G, G, G, G, G, G, G, G, G, E},
            {E, G, CV1, G, G, G, G, G, G, G, G, G, G, G, G, G, S, G, G, G, G, G, E},
            {E, G, G, G, G, G, G, G, G, G, G, G, G, G, G, G, G, G, G, G, G, G, E},
            {E, G, G, G, G, G, G, G, G, G, G, G, G, G, G, G, G, G, G, G, G, G, E},
            {E, G, G, G, G, O, G, G, G, G, G, G, G, G, G, G, G, G, G, G, G, G, E},
            {E, G, G, G, O, O, O, G, G, G, G, G, G, G, G, G, G, G, G, G, G, G, E},
            {E, G, G, O, O, O, O, O, G, G, G, G, G, G, C, C, C, C, G, G, G, C, E},
            {E, G, G, O, O, O, O, O, G, G, G, G, C, C, C, C, C, C, C, C, C, C, E},
            {E, G, G, G, O, O, O, G, G, G, G, C, C, M, M, M, M, M, M, M, M, M, E},
            {E, G, G, G, G, O, G, G, G, G, G, C, M, M, M, M, M, M, PH1, M, M, M, E},
            {E, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E}
            };
    public static ImageView[][] graphic_world_map = {
            {game_activity.ererr1,game_activity.ererr2,game_activity.ererr3,game_activity.ererr4,game_activity.ererr5,game_activity.ererr6,game_activity.ererr7,game_activity.ererr8,game_activity.ererr9,game_activity.ererr10,game_activity.ererr11,game_activity.ererr12,game_activity.ererr13,game_activity.ererr14,game_activity.ererr15,game_activity.ererr16,game_activity.ererr17,game_activity.ererr18,game_activity.ererr19,game_activity.ererr20,game_activity.ererr21,game_activity.ererr22,game_activity.ererr23},
            {game_activity.ererr68,game_activity.glass1,game_activity.glass2,game_activity.glass3,game_activity.glass4,game_activity.sea1,game_activity.glass5,game_activity.glass6,game_activity.glass7,game_activity.glass8,game_activity.glass9,game_activity.jyari1,game_activity.glass10,game_activity.glass11,game_activity.glass12,game_activity.glass13,game_activity.glass14,game_activity.glass15,game_activity.home1,game_activity.glass17,game_activity.glass18,game_activity.glass19,game_activity.ererr24},
            {game_activity.ererr67,game_activity.glass20,game_activity.glass21,game_activity.glass22,game_activity.sea2,game_activity.sea3,game_activity.sea4,game_activity.glass23,game_activity.glass24,game_activity.glass25,game_activity.glass26,game_activity.jyari2,game_activity.jyari3,game_activity.glass27,game_activity.glass28,game_activity.glass29,game_activity.glass30,game_activity.glass31,game_activity.glass32,game_activity.glass33,game_activity.glass34,game_activity.glass35,game_activity.ererr25},
            {game_activity.ererr66,game_activity.glass36,game_activity.glass37,game_activity.sea5,game_activity.sea6,game_activity.sea7,game_activity.sea8,game_activity.sea9,game_activity.glass38,game_activity.glass39,game_activity.glass40,game_activity.glass161,game_activity.jyari4,game_activity.jyari5,game_activity.jyari6,game_activity.jyari7,game_activity.jyari8,game_activity.jyari9,game_activity.jyari10,game_activity.jyari11,game_activity.jyari12,game_activity.jyari13,game_activity.ererr26},
            {game_activity.ererr65,game_activity.glass41,game_activity.glass42,game_activity.sea10,game_activity.sea11,game_activity.sea12,game_activity.sea13,game_activity.sea14,game_activity.glass43,game_activity.glass44,game_activity.glass45,game_activity.glass46,game_activity.glass47,game_activity.glass48,game_activity.jyari14,game_activity.jyari15,game_activity.jyari16,game_activity.jyari17,game_activity.glass49,game_activity.glass50,game_activity.glass51,game_activity.jyari18,game_activity.ererr27},
            {game_activity.ererr64,game_activity.glass52,game_activity.glass53,game_activity.glass54,game_activity.sea15,game_activity.sea16,game_activity.sea17,game_activity.glass55,game_activity.glass56,game_activity.glass57,game_activity.glass58,game_activity.glass59,game_activity.glass60,game_activity.glass61,game_activity.glass62,game_activity.glass63,game_activity.glass64,game_activity.glass65,game_activity.glass66,game_activity.glass67,game_activity.glass68,game_activity.glass69,game_activity.ererr28},
            {game_activity.ererr63,game_activity.glass70,game_activity.glass71,game_activity.glass72,game_activity.glass73,game_activity.sea18,game_activity.glass74,game_activity.glass75,game_activity.glass76,game_activity.glass77,game_activity.glass78,game_activity.glass79,game_activity.glass80,game_activity.glass81,game_activity.glass82,game_activity.glass83,game_activity.glass84,game_activity.glass85,game_activity.glass86,game_activity.glass87,game_activity.glass88,game_activity.glass89,game_activity.ererr29},
            {game_activity.ererr62,game_activity.glass90,game_activity.glass91,game_activity.glass92,game_activity.glass93,game_activity.glass94,game_activity.glass95,game_activity.glass96,game_activity.glass97,game_activity.glass98,game_activity.glass99,game_activity.glass100,game_activity.glass101,game_activity.glass102,game_activity.glass103,game_activity.glass104,game_activity.glass105,game_activity.glass108,game_activity.glass109,game_activity.glass110,game_activity.glass111,game_activity.glass112,game_activity.ererr30},
            {game_activity.ererr61,game_activity.glass113,game_activity.glass114,game_activity.glass115,game_activity.glass116,game_activity.glass117,game_activity.glass118,game_activity.glass119,game_activity.glass120,game_activity.glass121,game_activity.glass122,game_activity.glass123,game_activity.glass124,game_activity.glass125,game_activity.glass126,game_activity.glass127,game_activity.glass128,game_activity.glass129,game_activity.glass130,game_activity.glass131,game_activity.glass132,game_activity.glass133,game_activity.ererr31},
            {game_activity.ererr60,game_activity.glass134,game_activity.cave_entrance1,game_activity.glass136,game_activity.glass137,game_activity.glass138,game_activity.glass139,game_activity.glass140,game_activity.glass141,game_activity.glass142,game_activity.glass143,game_activity.glass144,game_activity.glass145,game_activity.glass146,game_activity.glass147,game_activity.glass148,game_activity.store1,game_activity.glass150,game_activity.glass151,game_activity.glass152,game_activity.glass153,game_activity.glass154,game_activity.ererr32},
            {game_activity.ererr59,game_activity.glass155,game_activity.glass156,game_activity.glass157,game_activity.glass158,game_activity.glass159,game_activity.glass160,game_activity.glass162,game_activity.glass163,game_activity.glass164,game_activity.glass165,game_activity.glass166,game_activity.glass167,game_activity.glass168,game_activity.glass169,game_activity.glass170,game_activity.glass171,game_activity.glass172,game_activity.glass173,game_activity.glass174,game_activity.glass175,game_activity.glass176,game_activity.ererr33},
            {game_activity.ererr58,game_activity.glass177,game_activity.glass178,game_activity.glass179,game_activity.glass180,game_activity.glass181,game_activity.glass182,game_activity.glass183,game_activity.glass184,game_activity.glass185,game_activity.glass186,game_activity.glass187,game_activity.glass188,game_activity.glass189,game_activity.glass190,game_activity.glass191,game_activity.glass192,game_activity.glass193,game_activity.glass194,game_activity.glass195,game_activity.glass196,game_activity.glass197,game_activity.ererr34},
            {game_activity.ererr57,game_activity.ererr56,game_activity.ererr55,game_activity.ererr54,game_activity.ererr53,game_activity.ererr52,game_activity.ererr51,game_activity.ererr50,game_activity.ererr49,game_activity.ererr48,game_activity.ererr47,game_activity.ererr46,game_activity.ererr45,game_activity.ererr44,game_activity.ererr43,game_activity.ererr42,game_activity.ererr41,game_activity.ererr40,game_activity.ererr39,game_activity.ererr38,game_activity.ererr37,game_activity.ererr36,game_activity.ererr35},
    };
}
