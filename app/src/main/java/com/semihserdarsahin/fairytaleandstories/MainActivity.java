package com.semihserdarsahin.fairytaleandstories;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    CompositeDisposable compositeDisposable=new CompositeDisposable();
    StoryDatabase db;
    Dao dao;
    ArrayList<String> textContexts;
    ArrayList<String> textIntros;
    ArrayList<String> textHeaders;

    SharedPreferences sharedPreferences;
    boolean uploaded;
    int[] imagesArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textContexts=new ArrayList<>();
        textIntros=new ArrayList<>();
        textHeaders=new ArrayList<>();


        db= Room.databaseBuilder(MainActivity.this,StoryDatabase.class,"Story").build();
        dao=db.dao();
        sharedPreferences=getSharedPreferences("com.semihserdarsahin.fairytaleandstories", Context.MODE_PRIVATE);
        uploaded=sharedPreferences.getBoolean("uploaded",false);


        if (uploaded==true){
        }
        else{
            setTextHeaders();
            addContexts();
            addIntors();
            imagesArray = new int[]{
                    R.drawable.aladdinvesihirlilamba, R.drawable.alibabave4haramiler, R.drawable.alice,
                    R.drawable.altinsacli, R.drawable.bambi, R.drawable.bremenmizika, R.drawable.cirkinordek, R.drawable.denizkizi, R.drawable.findikkiran,
                    R.drawable.grinch, R.drawable.hansel, R.drawable.heidi, R.drawable.jackvefasulye, R.drawable.keloglan, R.drawable.kibritcikiz,
                    R.drawable.kirmizsibas, R.drawable.kiyafetsizkral, R.drawable.kulkedisi, R.drawable.littleprince, R.drawable.moana, R.drawable.paddington, R.drawable.pamukprenses,
                    R.drawable.peterpan, R.drawable.petertavsan, R.drawable.pinokyo, R.drawable.ponyo, R.drawable.prensesvebezelye, R.drawable.pussinboots, R.drawable.rapunzel,
                    R.drawable.siyahinci, R.drawable.smurfs, R.drawable.tomsawyer, R.drawable.uckucukdomuz, R.drawable.yuzbirdalmacya
            };

            System.out.println(textContexts.size());
            System.out.println(textIntros.size());
            System.out.println(imagesArray.length);
            System.out.println(textHeaders.size());

            List<Story> storyList = new ArrayList<>();
            for (int i = 0; i < textContexts.size(); i++) {
                Story story = new Story(textHeaders.get(i), textContexts.get(i), imagesArray[i], textIntros.get(i));
                storyList.add(story);
                compositeDisposable.add(dao.insert(storyList.get(i)).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(this::handleResponse));

                System.out.println(textHeaders.get(i));
                System.out.println(textIntros.get(i));
                System.out.println(textContexts.get(i));
                System.out.println("------------------------------------------------------");
            }

            sharedPreferences.edit().putBoolean("uploaded",true).apply();
        }


    }
    void handleResponse(){

    }
    public void about(View view){
        Intent intent=new Intent(MainActivity.this,AboutActivity.class);
        startActivity(intent);
    }
    public void browse(View view){
        Intent intent=new Intent(MainActivity.this,StoriesActivity.class);
        startActivity(intent);
    }
    public void exit(View view){
        finish();
    }
    public void feedback(View view){
        Intent intent=new Intent(MainActivity.this,FeedBackActivity.class);
        startActivity(intent);
    }
    public void addContexts(){
        textContexts.add("Once upon a time in a distant city, there lived a young and impoverished lad named Aladdin. Aladdin scraped by doing odd jobs in the bustling market. One fateful day, while wandering through the city's streets, he encountered an old man sitting in front of an antiquities shop. Unbeknownst to Aladdin, this old man was a sorcerer.The sorcerer approached Aladdin and offered to sell him an old, tarnished lamp for just a few pieces of gold. The lamp looked worn and unimpressive, but the sorcerer insisted that it was a special lamp with a great treasure inside. Aladdin hesitated at first but decided to part with some of his gold to acquire the lamp.As Aladdin began to clean the lamp, a sudden cloud of smoke billowed out, revealing a beautiful genie-like being who introduced herself as a magical genie. The genie granted Aladdin three wishes.For his first wish, Aladdin wished to become a wealthy prince, and in an instant, his life was transformed. The city marveled at his newfound wealth and splendor. However, Aladdin soon realized that material riches didn't bring happiness.Before using his second wish, Aladdin, using the magic lamp, made his way to the palace of the city where he met the princess and fell in love. Their love story began, but their happiness was short-lived as the wicked sorcerer returned, kidnapped the princess, and demanded the lamp.Aladdin, using his third and final wish, requested the genie's help to defeat the sorcerer and rescue the princess. With the genie's magic, Aladdin emerged victorious and won the heart of the princess.Aladdin and the princess lived happily ever after, grateful to the magic lamp and the kind genie within. The story serves as a reminder that true happiness comes from the heart, and material wealth is not as important as inner qualities.");
        textContexts.add("Once upon a time, in a distant land, there lived a poor woodcutter named Ali Baba. He struggled to make ends meet, while his brother Kasim was a wealthy merchant. One day, Ali Baba stumbled upon a group of forty thieves who hid their stolen treasures in a cave in the nearby mountains. He accidentally discovered their secret hideout when he overheard their leader, saying, \"Open, Sesame.\"Ali Baba returned to the cave later with a plan. He repeated the words \"Open, Sesame,\" and the cave's entrance magically opened. He entered and found the thieves' treasure. Filling his bags with gold and jewels, he left. However, he failed to remember the command words to exit the cave.Fearing for his life, he sought help from his clever slave, Morgiana, who discovered the thieves' plot to murder Ali Baba. Morgiana cleverly marked the doors of Ali Baba and Kasim, so she could identify the thieves. When Kasim entered the cave, the thieves killed him, but Morgiana managed to save her master.The thieves soon realized that someone had discovered their secret, and they sought revenge. Ali Baba and Morgiana devised a plan to defeat them. Ali Baba's other brother, Qasim, had married the chief thief's sister. Ali Baba and Qasim's widow invited the thieves to dinner. Morgiana cleverly killed them all while they were distracted.The thieves were defeated, and Ali Baba's family became wealthy from the stolen treasure. Ali Baba generously shared the fortune with his loyal slave Morgiana and lived happily ever after, with the lesson that honesty, cleverness, and loyalty triumph over greed and treachery.");
        textContexts.add("It was a warm summer day and Alice was getting bored sitting beside her sister, who had her nose buried in a book. Suddenly, a little White Rabbit with pink eyes ran in front of her shouting, \"On dear, oh dear, I'm late.\"The Rabbit pulled a watch out of his pocket to check the time. He shook his head, then disappeared down a rabbit's hole. \"I must find out why he's in such a hurry!\" cried Alice. Filled with curiosity, she ran to the rabbit's hole and peeped through the entrance.The hole dropped suddenly and Alice fell. \"When will I ever reach the bottom of this dreadful hole?\" she shouted, while falling helplessly downwards.Finally she landed in a long, narrow hallway with doors of many sizes. On a three-legged table, Alice found a tiny gold key and a green bottle that said \"DRINK ME\". \"This key must fit one of the doors,\" she said.\"It's the one behind the table,\" she cried, \"but I'm too big to fit through such a little door. May be the potion in that bottle will help me,\" she decided. And she drank it.Alice began to shrink until she was no bigger than a doll. She opened the door and quickly ran through it. \"What a splendid garden!\" she exclaimed. \"Why, I'm no bigger than the insects that crawl on these flowers.\" But the excitement soon wore off. Alice grew bored with her tiny size. \"I want to be big again,\" she shouted.Her shouts startled the White Rabbit, who ran past her again. Mistaking her for his maid, he ordered, \"Go to my cottage and fetch my gloves and fan.\"Alice was confused by the Rabbit's behaviour. \"May be I'll find something at the cottage to help me,\" she said hopefully.A piece of chocolate cake was kept on a table by the doorway. Next to the cake was a note that read \"EAT ME\". \"I'm so hungry,\" Alice said as she ate the cake. \"I feel strange. Oh no! I've grown larger than this house!\" she cried.\"Get out of my way! You're blocking thedoor!\" shouted the White Rabbit. Alice managed to pick up his fan. Immediately, she began to shrink.\"Oh, I'll never get back to the right size,\" Alice cried. She went looking for help. Soon, she saw a green caterpillar dressed in a pink jacket. He was sitting on the top of a large mushroom, smoking a bubble pipe. \"One side makes you big, the other side makes you small,\" he said to Alice before slithering away.\"One side of what?\" Alice called after him.\"The mushroom, silly,\" he answered.Alice ate a piece of the mushroom.\"Thank goodness, I'm growing!\" she cried, \"But which way do I go?\"\"That path leads to the Mad Hatter. The other way leads to -Lae March Hare,\" said a voice. Alice turned to find a smiling Cheshire Cat in a tree. \"I'll see you later at the Queen's croquet game,\" he said before disappearing.Alice walked down a path, \"How lovely! A tea party,\" she thought.\"There's no room for you!\" shouted the Mad Hatter, \"You may stay if you answer my riddle.\" Alice smiled. She loved riddles.After several riddles, Alice became confused. \"Every time I answer, you ask a question,\" she told the Mad Hatter.\"We don't know any answers,\" he giggled. \"This is a waste of time,\" scolded Alice. The others ignored her. They were trying to wake the Dormouse.Alice continued her walk. She found herself in the middle of a field where the Queen of Hearts was playing croquet. Her guards and gardeners were shaped like cards. One gardener had planted white roses by mistake and then painted them red, \"Off with their heads!\" shrieked the Queen. \"I hate white roses!\" \"Have you ever played croquet?\" the Queen asked Alice.\"Yes,\" Alice timidly answered. \"But I've never used a flamingo or a hedgehog.\" \"Play with me!\" ordered the Queen.\"And let me win or I'll have your head!\" Alice tried her best to play we,l, but she had trouble with her flamingo. \"Off with her head!\" cried the Queen. Just then a trumpet sounded at the distance calling court to session.Everyone rushed into the courtroom. \"Court is now in session,\" announced the White Rabbit, \"Will Alice please come to the stand?\" Alice took the stand and looked at the jury box, where the March Hare and the Mad Hatter were making noise. The Dormouse slept and the Cheshire Cat smiled at her. \"What's going on?\" asked Alice.\n" +
                "\"You are guilty of stealing the delicious heart-shaped tarts!\" accused the Queen, \"And now you must be punished. Off with her head Off with her head!\" yelled the Queen.\n" +
                "\"How silly,\" replied Alice. \"I did not have the slightest idea what you were talking about! I was only playing croquet.\"\n" +
                "Alice felt someone touch her shoulder, \"Wake up. You've been sleeping for too long,\" said her sister softly.\n" +
                "\"I had a strange dream,\" said Alice. She told her sister about the White Rabbit, the mad tea party, the Queen of Hearts and the trial. But her sister wasn't paying attention. \"You're reading again,\" mumbled Alice. As she stretched, Alice saw a little White Rabbit with pink eyes scurry behind a tree.\n" +
                "The End");
        textContexts.add("Goldilocks and the Three Bears is a classic fairy tale that tells the story of a young girl named Goldilocks. One day, while wandering in the forest, she comes across a cozy cottage that belongs to a family of three bears: Mama Bear, Papa Bear, and Baby Bear. Curiosity gets the better of her, and she enters the cottage.\n" +
                "\n" +
                "Inside, she finds three bowls of porridge on the table. She tries each one to find the perfect temperature and taste, but she only finds Baby Bear's porridge to be just right. She then goes to the living room and tries out the chairs. She breaks Baby Bear's chair as it's too small for her.\n" +
                "\n" +
                "Goldilocks, feeling tired, goes upstairs to find three beds. She tests each bed and finds Baby Bear's bed to be the most comfortable. She falls asleep.\n" +
                "\n" +
                "The bears return home and discover the mess. They notice that someone has been in their home, and Baby Bear is upset that his porridge is gone, his chair is broken, and a girl is sleeping in his bed.\n" +
                "\n" +
                "Goldilocks awakens, terrified by the bears' presence, and quickly escapes through a window. She learns her lesson about respecting others' property and not entering their homes without permission.\n" +
                "\n" +
                "The story of Goldilocks and the Three Bears teaches children about the importance of respecting others' belongings and the consequences of trespassing. It's a beloved classic that continues to be a popular tale for young readers.");
        textContexts.add("One morning, a little rabbit named Tambor went to wake up the owl to go and see a little fawn that had just been born. All the animals of the forest got together to meet the new baby deer, Bambi! Everyone became really good friends with him and they started showing him everything in the forest, like the flowers, the rivers, and they told him the names of all the animals. Every day, they got together in the clearing to play and learn. Also, read Dumbo- The Flying Elephant.\n" +
                "\n" +
                "Image Source–> in.pinterest.com. One morning, Bambi’s mother took him to see his father who was the head of the herd of deer. It was his job to look after all of the rest of the deer. When Bambi and his father were casually walking in the forest, they heard dogs barking. “Run Bambi run!” said his father, trying to save him. “But why father?” cried, Bambi. “It’s the humans, and every time they come to the forest, they try to hunt us.”\n" +
                "\n" +
                "A few days passed after this encounter with the humans and Bambi’s father showed him all he needed to know for when the day came for Bambi to be the head of the herd. Bambi then met another deer that was very beautiful. Her name was Farina and they fell in love with each other immediately.\n" +
                "\n" +
                "One day, both of them were playing when they heard the sound barking of the dogs. Bambi thought to himself, “Oh, no! It’s the humans!” and he tried to find the cover and hide. By the time he thought of a plan, the dogs were so close that if he wanted to save Farina, he was left with no choice but to stand between her and the dogs. Once she was safe, Bambi tried to save himself and run from the dogs. You may also like to read, What Tiggers Do Best.\n" +
                "\n" +
                "Just when he thought he was free, he encountered a cliff that he had to jump. The hunters fired their shots missing Bambi but the fall injured him when he landed by the river. At that moment, his father and all his friends came to help him cross the river. Once they crossed the river, they would be free from the danger of the humans. When they made it cross, they tended to Bambi’s wounds and he made a swift recovery.\n" +
                "\n" +
                "Time passed and our hero, Bambi grew up to become a big buck. He went to see his friends and it was hard for them to recognize him because he had grown so much. Now, he even had big, beautiful antlers. By this time, the owl was old and wise and Tambor had married a little rabbit and they had three baby rabbits.\n" +
                "\n" +
                "Bambi married Farina and they had a little fawn. Like Bambi did in his childhood, his baby followed his footsteps. The baby deer went to meet all the animals of the forest just like Bambi did when he was young. Everyone lived very happily and Bambi was now the head of the herd. Just like his father before him, who had become too old t look after the others. So now, it was Bambi’s job to look after the forest.");
        textContexts.add("On a farm long ago, a Mama Duck sat on her nest.  “How long must I wait for my babies to hatch?” she said.  “I have to sit here all alone! And no one comes to visit me.”  But what could she do? A Mama duck must keep her eggs warm till they hatch. At last, the eggs began to crack.  One by one, yellow ducklings stepped out of their shells.  They shook their wings and said, “Quack, quack!” Ezoic “Look at all of you!” said Mama Duck with joy.  “You are all so cute!” “Quack, quack!” they said. Mama Duck said, “Come and line up.  We will go down to the lake for your very first swim.”  She counted – one, two, three, four, five. “Oh dear!” she said.  “I should have six ducklings!” But one large egg was still in the nest. “Well,\" said Mama Duck, \"it looks like that big egg will take more time.” So she had to go sit on her nest again and wait some more. The Ugly Duckling The next day, the big egg started to hatch.  Out came a baby boy bird. But if one may say so, it was an odd-looking thing.  This bird was much bigger than others. He was not yellow at all - he was dark-gray from his head to his feet.  And he walked with a funny wobble. One of the yellow ducklings pointed.  “What is THAT? He cannot be one of us!” “I have never seen such an ugly duckling!” said another. “How can you say such a thing?” said Mama Duck in a stern voice. “You are only one day old!  Your brother hatched from the very same nest as you did. Now line up. We will go to the lake for your very first swim.” Yet the other ducklings quacked, “Ugly!  Ugly! Ugly!” The Ugly Duckling did not know why the other ducklings were yelling at him.  He took the last spot in the line. Ezoic Each yellow duck jumped in the river and swam behind Mama Duck.  When it was his turn, the Ugly Duckling jumped in and started to paddle, too.  “At least he can swim,” Mama Duck said to herself. When they left the water and started to play, the Ugly Duckling tried to play with his brothers and sisters, too.  They yelled, “Go away! We will not play with you! You are ugly. And you walk weird, too!” The Ugly Duckling When Mama Duck was close by, she would not let them talk in this way.  “Be nice!” she would scold. But she was not always close by. One day, one of the yellow ducklings said to the Ugly Duckling, “You know what?  You would do us a big favor if you just went away from here!” All of them started to quack, “Get out!  Get out! Get out!” Ezoic “Why won’t they let me stay here?” said the Ugly Duckling to himself.  He hung his head down low. “Ah, they are right. I should go.” That night, the Ugly Duckling flew over the farmyard fence.  He flew till he landed on the other side of the lake.  There he met two grown-up ducks. The Ugly Duckling “Can I please stay here for awhile?” said the Ugly Duckling.  “I have nowhere else to be.” “What do we care?” said one of the ducks.  “Just don’t get in our way.” “Woof! Woof!”  Suddenly a big hungry dog came tearing by, chasing the two ducks.  They quickly flew up in the air, and their feathers fell down on the ground.  The poor Ugly Duckling froze in fear. The dog sniffed and sniffed at the Ugly Duckling, then turned away.  “I am too ugly even for the big hungry dog to want,” said the Ugly Duckling with his head hung low. The sky turned dark.  Crack!  A bolt of lightning.  Then came a big storm, with heavy rains pouring down from the sky.  In just moments, the Ugly Duckling was soaked through and through. Then a cold wind started to blow. “Brrr!” he said with both wings held close to his chest.  “If only there was a place I could get dry.” All at once, a tiny light blinked far off in the woods.  “Could it be someone’s hut?” He flew to the door.  “Quack?” said the Ugly Duckling.  The door of the hut creaked open. Ezoic “What is all this noise?” said an old woman, looking right and left.  Her eyes were not that good. Then she looked down. “Ah, look at that, it’s a duck!”  She picked up the Ugly Duckling and dropped him inside her hut. “You can stay here, but only if you lay eggs,” she said. A tomcat and hen crept up to the Ugly Duckling.  “Who do you think you are, coming in here and taking up room by the fire!” said the tomcat. “Squawk!” said the hen.  “I do not need anyone else in this hut laying eggs.” “Do not worry about that,” said the Ugly Duckling.  “I am a boy duck.” “Then why are you still here?” said the tomcat.  “Did you not hear what the old woman said?” “Get out of here, pretender!” clucked the hen. “Get out!  Get out!“ hissed the tomcat. Ezoic The door was still a bit open, so our poor Ugly Duckling slipped out the door, and back into the storm. “No one ever wants me,” said the Ugly Duckling with a tear in his eye. The Ugly Duckling The storm ended.  Soon he found a new lake.  Looking into the water, the Ugly Duckling saw the reflection of a flock of large white birds flying.  He looked overhead and could not believe what he saw. There, above him, were the most beautiful birds he had ever seen!  Their long white bodies and slender necks seemed to just glide through the sky. He watched until the very last bird had winged its way out of view. He stayed at that lake all by himself, and time passed.  The leaves of the trees turned deep red and gold, and then the leaves fell to the ground.  Winter came, setting a blanket of white snow all over. The cold wind and the dark clouds made the Ugly Duckling feel even more sad. He had to go into the cold, cold lake to fish, but it was getting harder to swim.  The lake was turning to ice. One day, it was all he could do was to paddle the water to keep it from freezing around him, and trapping him in the lake. “I am so tired!” he said, paddling with all his might.  The ice got thicker and drew closer to him. In a moment, two giant hands swept him up.  “You poor thing!” said a farmer. He held the Ugly Duckling close to his thick wool jacket and took the bird to his home. Ezoic Never was a warm fireplace more welcome!  For the rest of the winter, the farmer cared for the Ugly Duckling.  Then spring came. Tips of green covered the trees. Short, bright flowers popped up from the ground. “It is time for you to go to the lake to swim again, as you were born to do,” said the farmer.  He took the duckling back to the lake where he had found him, and set him with care on the water. “Gosh, I feel strong,” said the young bird, flapping his wings.  “Why, I never felt as strong as I do right now!” He heard quiet splashing sounds behind him, and turned around.  A flock of those same beautiful birds he had seen in the sky before landed behind him on the water. “Do not worry!” he said to them, holding out one wing.  “I will go now. I will not make trouble for you.” A big fat tear rolled down his cheek.  He turned to go away. When he opened his eyes, he saw a reflection in the water of one of those beautiful white birds.  Why was it so close to him? He jumped back. And the reflection jumped back, too. The Ugly Duckling “What is this?” he said.  He stretched his neck, and the reflection of the beautiful bird stretched its neck, too. Ezoic “Why are you going so soon?” said one of the beautiful birds. “Stay here, with us!” said another.  “We’ll be great friends.” Then, the bird who used to be the Ugly Duckling knew what had happened!  He was no longer an ugly gray bird that wobbled when it walked. At one moment, all the swans flapped their wings and took off into the sky. “Come with us,” one called back. “Take the lead!” So he flapped his wings fast and took his place in front of the whole flock.  All his new friends flapped their wings behind him. “Say!” he said, gliding and dipping through the sky as he sped on.  “Who’s an ugly duckling now? Surely, Not I!”");
        textContexts.add("Once upon a time, on a beautiful farm in the countryside, there was a mother duck who had been patiently waiting for her eggs to hatch. One by one, her eggs cracked open, and little ducklings emerged, all of them fluffy and cute. However, to her surprise, there was one egg left, and when it hatched, it revealed a different-looking duckling.\n" +
                "\n" +
                "This duckling was not like the others. It was big, awkward, and had gray feathers. The other ducklings and even the farm animals made fun of it. The poor duckling felt lonely and unloved, believing it was indeed an ugly duckling.\n" +
                "\n" +
                "One day, the ugly duckling couldn't take it anymore and decided to leave the farm. It embarked on a journey, hoping to find a place where it would be accepted and loved for who it was. Throughout its journey, the duckling encountered various animals and people, but none of them were kind to it.\n" +
                "\n" +
                "As the seasons changed, the ugly duckling grew and transformed. Its gray feathers turned into beautiful white ones, and it realized it had become a magnificent swan. The swan gazed at its reflection in the water and was stunned by its own beauty.\n" +
                "\n" +
                "Then, one day, the swan came across a flock of other swans, and they welcomed it with open wings. The swan realized it had found where it truly belonged - among its own kind.\n" +
                "\n" +
                "The ugly duckling had turned into a graceful and elegant swan, and it was finally happy. It learned that it had never been an ugly duckling but a beautiful swan all along.\n" +
                "\n" +
                "The moral of the story is that beauty is not just skin deep. What might appear ugly on the outside can hide great beauty within. It's a tale of self-discovery, acceptance, and the importance of embracing our differences.");
        textContexts.add("Once upon a time, in a beautiful underwater kingdom, there lived a young mermaid named Ariel. She had long, flowing red hair and a voice as sweet as music. Ariel was curious and adventurous, always exploring the ocean's depths.\n" +
                "\n" +
                "One day, while swimming near the surface, Ariel saw a magnificent ship with humans on board. Among them was a handsome prince named Eric. Ariel was instantly captivated by him, and she longed to be part of the human world.\n" +
                "\n" +
                "Ariel's fascination with the human world grew, and she made a daring decision. She went to the sea witch, Ursula, who had the power to grant her legs in exchange for her voice. Ariel agreed to the deal and, with her beautiful voice gone, became human.\n" +
                "\n" +
                "With her new legs, Ariel made her way to the surface and was found by Prince Eric after a shipwreck. He was drawn to her beauty, but she couldn't speak to tell him who she was. They spent time together, and Ariel hoped that Eric would fall in love with her before the sunset on the third day, or she would lose her chance to become human forever.\n" +
                "\n" +
                "Ariel and Eric shared wonderful moments, and love began to blossom. But Ursula, the sea witch, had other plans. She used Ariel's voice to trick Eric into almost marrying her instead. However, with the help of her friends, Sebastian the crab and Flounder the fish, Ariel stopped Ursula's evil plan and got her voice back.\n" +
                "\n" +
                "In the end, Ariel and Eric's love prevailed. Ariel's father, King Triton, realized how much she loved Eric and how he had been wrong to keep her away from the human world. He used his magic to transform her into a human permanently so she could be with Eric.\n" +
                "\n" +
                "Ariel and Eric got married and lived happily ever after. Ariel became part of the human world and found her true love, and Eric realized the importance of the ocean and its wonders.");
        textContexts.add("Once upon a time, many years ago, there lived a sweet girl named Clara. Clara's family would host a grand party every Christmas Day, and a significant tradition at these gatherings was the arrival of Clara's uncle Drosselmeyer, who brought special gifts for Clara and her brother Fritz. However, this year, Clara received a most extraordinary gift. Uncle Drosselmeyer presented Clara with a Nutcracker doll, and it looked charming and mysterious to her.\n" +
                "\n" +
                "When midnight came, Clara decided to spend some time with her Nutcracker doll. But that night, things took an unexpected turn. The Nutcracker doll miraculously came to life in Clara's room and led her into a magical adventure.\n" +
                "\n" +
                "The Nutcracker, it turned out, was a prince under a spell, and Clara was now thrust into a world where dreams and dimensions intertwined. Clara and the Nutcracker passed through a magical door and found themselves in the middle of an enchanted forest, surrounded by an army of the Rat King.\n" +
                "\n" +
                "The Nutcracker was, in fact, a prince, ensnared by the Rat King's spell. Clara resolved to help the prince, and she lent her support in the magical battle that ensued. At the end of the adventure, Clara and the prince defeated the Rat King and broke the enchantment.\n" +
                "\n" +
                "Following their victory, the prince and Clara journeyed to the Land of the Sweets. This magical realm was filled with sweet treats, dancing flowers, and the enchanting wonders of fairy tales. Clara and the prince met various dancers and explored the beauty of the Land of the Sweets.\n" +
                "\n" +
                "But every adventure must come to an end, and it was time for Clara to return to the real world. Clara, with the prince, made her way back to her own world. The prince was no longer under the curse and had transformed into a true prince.\n" +
                "\n" +
                "When Clara woke up and returned to the real world, she found the Nutcracker doll by her side. This indicated to her that the incredible adventure she'd experienced was more than just a dream. Clara continued to celebrate a beautiful Christmas with her family and loved ones.");
        textContexts.add("Once upon a time, in the whimsical town of Whoville, there lived a creature known as the Grinch. The Grinch was a tall, green, and rather grumpy character who despised Christmas with all his heart. He lived in a cave high above Whoville and couldn't stand the cheerful celebrations of the holiday season that took place below.\n" +
                "\n" +
                "As Christmas approached, the people of Whoville were filled with joy and anticipation, decorating their homes, singing songs, and exchanging gifts. The Grinch, however, couldn't stand the happiness that surrounded him. He decided to do something drastic; he would steal Christmas from the Whos.\n" +
                "\n" +
                "On Christmas Eve, the Grinch dressed as Santa Claus and, with his trusty dog Max as a reindeer, descended into Whoville. He went from house to house, taking presents, decorations, and even the feast prepared for the Christmas dinner. He believed that by taking away all the trappings of Christmas, he could prevent the holiday from happening.\n" +
                "\n" +
                "The Grinch's plan seemed to work as he loaded all the stolen items onto a sleigh and prepared to dump them off a nearby mountain. However, as he stood on the precipice, he heard the Whos down in Whoville singing Christmas songs and celebrating, despite the absence of their material possessions.\n" +
                "\n" +
                "It was at this moment that the Grinch had a change of heart. He realized that Christmas was about more than just gifts and decorations; it was about love, togetherness, and the joy of being with loved ones. The Grinch's heart grew three sizes, and he rushed back to Whoville to return all the stolen items.\n" +
                "\n" +
                "The Whos welcomed the Grinch with open arms and forgave him for his misdeeds. They celebrated together, and the Grinch carved the roast beast for their Christmas feast. The Grinch had not only saved Christmas but had also found the true meaning of the holiday.");
        textContexts.add("Once upon a time in a small village, there lived a poor woodcutter and his wife. They had two children named Hansel and Gretel. Times were tough, and the family struggled to find enough food to eat. The stepmother, who was unkind and wicked, suggested leaving Hansel and Gretel in the forest because there wasn't enough food to share.\n" +
                "\n" +
                "Hansel overheard their plan and, that night, he cleverly collected shiny pebbles and left a trail from their home to the deep, dark forest. The next day, the woodcutter and his wife took Hansel and Gretel into the woods and pretended to leave them behind. The children were frightened but used the pebbles to find their way back home.\n" +
                "\n" +
                "The stepmother was furious when they returned. A few days later, she insisted on trying again and led Hansel and Gretel even deeper into the forest. This time, Hansel had only breadcrumbs to leave a trail. But birds in the forest ate the breadcrumbs, and the children became hopelessly lost.\n" +
                "\n" +
                "As they wandered through the forest, they came across a strange, gingerbread house. It was adorned with candy, frosting, and all sorts of sweets. Hungry and tired, Hansel and Gretel couldn't resist, and they began to eat pieces of the house. Little did they know that the house belonged to a wicked witch.\n" +
                "\n" +
                "The witch lured them inside with promises of delicious food. She locked Hansel in a cage and made Gretel her servant. The witch had a plan to fatten up Hansel and then eat him. Each day, she would check to see if he had grown plump enough, but Hansel would cleverly stick out a bone for the witch to feel.\n" +
                "\n" +
                "Gretel realized the witch's plan and, one day, when the witch was preoccupied, she shoved the witch into the oven and locked the door. Gretel freed Hansel, and they discovered the witch's treasure of jewels and escaped from the gingerbread house.\n" +
                "\n" +
                "They made their way back home, where they found their father grief-stricken and the stepmother gone. With the jewels, they were able to live happily ever after.");
        textContexts.add("heidi");
        textContexts.add("Once upon a time, in a small cottage, there lived a poor widow and her son, Jack. They were so poor that they had to sell their only cow to buy food. Jack was given the task of taking the cow to the market to get the best price. However, on his way to the market, he met a strange man who offered him a handful of magic beans in exchange for the cow. Jack, not knowing any better, traded the cow for the beans.\n" +
                "\n" +
                "When Jack returned home, his mother was furious with him for making such a foolish trade. She threw the beans out of the window in anger, and they fell into the ground. That night, something incredible happened. The beans grew into a gigantic beanstalk that stretched high into the sky.\n" +
                "\n" +
                "The next morning, Jack decided to climb the beanstalk to see where it led. He climbed and climbed until he reached a land high above the clouds. There, he discovered a colossal castle. Jack, curious and brave, entered the castle and found it inhabited by a giant. This giant had a vast treasure that included bags of gold coins, a magic hen that laid golden eggs, and a magical harp that could play beautiful music by itself.\n" +
                "\n" +
                "Jack, with the help of the magical hen and the harp, tried to take some of the treasures and escape without waking the giant. But as he was leaving, the giant awoke and began to chase him down the beanstalk. Jack quickly climbed down and, with his mother's help, chopped down the beanstalk. The giant fell to the ground, and Jack and his mother were safe.\n" +
                "\n" +
                "Jack and his mother lived happily ever after with the treasure they had taken from the giant's castle. They never went hungry again, and they learned the value of courage and resourcefulness.");
        textContexts.add("In a peaceful village nestled between the hills and the forests, there lived a remarkable young boy named Keloglan. He had a distinct feature that set him apart from the other children in the village: a head of pure white hair that he had since birth. People affectionately referred to him as Keloglan, which means \"Bald Boy\" in Turkish.\n" +
                "\n" +
                "Keloglan's appearance might have been unusual, but his intelligence, resourcefulness, and kind heart made him loved and respected by everyone in the village. He was always there to help, offer solutions, or bring a smile to someone's face with his witty jokes.\n" +
                "\n" +
                "One day, however, an ominous event cast a shadow over the village. A wicked sorcerer arrived, and his sinister presence brought nothing but trouble. This sorcerer was known for his malevolent spells and mischievous tricks. He decided to play a cruel prank on the village by casting a spell on their water source, causing it to dry up.\n" +
                "\n" +
                "With the water supply dwindling rapidly, the villagers were plunged into a state of panic. They couldn't drink, and their crops were withering. Faced with this dire situation, they turned to Keloglan, hoping that his cleverness might hold the key to solving the crisis.\n" +
                "\n" +
                "Keloglan decided to confront the sorcerer and find a way to break the spell that had afflicted their water source. Armed with determination and intelligence, he ventured into the unknown, on a journey that would test his wits and courage.\n" +
                "\n" +
                "Upon reaching the sorcerer's lair, Keloglan had to use all of his cleverness to outsmart the evil spellcaster. The sorcerer tried to trap him with illusions and riddles, but Keloglan's quick thinking and intelligence led him to discover the hidden incantation that held the water spell in place.\n" +
                "\n" +
                "Keloglan recited the spell in reverse, breaking the enchantment, and the water began to flow once more. The villagers were overjoyed and grateful beyond words. Keloglan had saved the day with his intelligence, and they hailed him as their hero.\n" +
                "\n" +
                "From that day forward, Keloglan's reputation as the cleverest boy in the village only grew. He continued to use his resourcefulness to help those in need, solve various challenges, and bring laughter to everyone around him.");
        textContexts.add("Once upon a time, on a bitterly cold New Year's Eve, in a city blanketed with snow, there lived a poor little girl. She was known as \"The Little Matchstick Girl.\" She was barefoot and shivering, wandering through the icy streets.\n" +
                "\n" +
                "The Little Matchstick Girl was desperately trying to sell her matches, but nobody seemed to notice or care. She wore ragged clothes and had no shoes to protect her feet from the freezing ground. As evening fell, she huddled in a corner, trying to keep warm.\n" +
                "\n" +
                "To escape her grim reality, the Little Matchstick Girl began to light her matches. Each matchstick she struck emitted a warm, comforting light. In the glow, she saw visions of a warm stove, a feast of food, and the joy of a loving family.\n" +
                "\n" +
                "In one match's light, she saw her grandmother, who had passed away and was the only person who had ever shown her love and kindness. She wished she could be with her grandmother, away from the cold and loneliness.\n" +
                "\n" +
                "The Little Matchstick Girl continued to strike matches, savoring the brief moments of warmth and happiness. But as the matches burned out, the harsh reality of the cold and the empty streets returned. She couldn't bear the cold any longer and knew that she was running out of matches.\n" +
                "\n" +
                "In a final act of desperation and hope, she struck all her remaining matches to create a brilliant blaze of light. In this radiant glow, she saw her beloved grandmother again. This time, her grandmother reached out and carried her away to a place where there was no more suffering, no more cold, and only warmth and love.\n" +
                "\n" +
                "The next morning, the people of the city found the lifeless body of the Little Matchstick Girl in the snow. She had passed away in the cold, but her face bore a peaceful expression, for she had found happiness and warmth in the end.");
        textContexts.add("Once upon a time, in a small village, there lived a sweet little girl known as Little Red Riding Hood. She was called that because she always wore a bright red hood that her grandmother had made for her. One day, her mother asked her to take a basket of goodies, including some delicious cakes, to her sick grandmother, who lived in a cottage deep in the woods.\n" +
                "\n" +
                "Little Red Riding Hood was excited about the journey, but her mother gave her a piece of advice: \"Stay on the path and don't talk to strangers.\" Little Red Riding Hood promised to obey her mother's words and set off on her way through the forest.\n" +
                "\n" +
                "As she walked through the woods, she met a sly wolf who stopped to chat with her. The wolf, knowing that Little Red Riding Hood was going to her grandmother's house, asked her about her destination. Innocently, she shared the location of her grandmother's cottage.\n" +
                "\n" +
                "The wolf, with a wicked plan in mind, hurried ahead of Little Red Riding Hood and reached the grandmother's cottage first. He knocked on the door and pretended to be Little Red Riding Hood, hoping to trick the old lady. The grandmother, who was unwell, didn't realize the deception and let the wolf inside.\n" +
                "\n" +
                "When Little Red Riding Hood eventually arrived, she noticed something unusual about her grandmother. \"Grandmother, what big eyes you have!\" she said. The wolf replied, \"All the better to see you with, my dear.\" Little Red Riding Hood then asked about the wolf's big ears, nose, and teeth, and each time the wolf responded with a similar answer.\n" +
                "\n" +
                "Finally, the wolf revealed himself and leaped out of the bed to swallow Little Red Riding Hood whole. But just in the nick of time, a nearby woodsman heard the commotion and rushed to the cottage. He bravely saved Little Red Riding Hood and her grandmother by chasing the wolf away.\n" +
                "\n" +
                "The wolf ran off into the woods, and Little Red Riding Hood and her grandmother were safe. They thanked the woodsman for his heroism and promised never to talk to strangers again. From that day on, they lived happily ever after.");
        textContexts.add("Once upon a time, in a grand and prosperous kingdom, there lived an emperor who was obsessed with his clothing. He had a different outfit for every hour of the day and loved to show off his luxurious wardrobe. He spent all his time and the kingdom's treasure on his clothes, often at the expense of his duties as a ruler.\n" +
                "\n" +
                "Two cunning weavers heard of the emperor's obsession and hatched a plan to take advantage of it. They arrived at the palace, claiming to be master weavers who could create the most exquisite and magical fabrics ever seen. They said that the clothes they wove had a remarkable quality: they were invisible to those who were unfit for their positions or were simpleminded.\n" +
                "\n" +
                "The emperor was intrigued by the idea of having these extraordinary clothes. He thought they would allow him to identify who among his subjects was wise and competent. He commissioned the weavers to start working on the new clothes right away.\n" +
                "\n" +
                "The weavers pretended to weave these magical clothes, but in reality, they did nothing at all, making empty looms appear as though they were in use. The emperor's advisors, who couldn't see any fabric being woven, were afraid to admit they couldn't see the clothes. They didn't want to be seen as unfit or simpleminded.\n" +
                "\n" +
                "When the \"new clothes\" were finally presented to the emperor, he was astonished. He looked at the empty looms and saw nothing, but he didn't want to admit that he couldn't see the fabric, fearing it would mean he was unfit for his position. So, he praised the weavers and decided to wear the new clothes in a grand parade through the city.\n" +
                "\n" +
                "On the day of the parade, the emperor walked through the streets wearing nothing but his imaginary clothes. The people in the crowd were bewildered, but no one wanted to admit they couldn't see the fabric, as they didn't want to appear unfit or simpleminded either.\n" +
                "\n" +
                "It was a young child who finally broke the silence. \"The emperor has no clothes!\" the child shouted, and soon the whole crowd began to murmur in agreement.\n" +
                "\n" +
                "Realizing the truth, the emperor was embarrassed but continued the parade, feeling more foolish than ever. He finally understood that his obsession with clothing and his vanity had blinded him to the reality.");
        textContexts.add("Once upon a time, in a faraway kingdom, there lived a young girl named Cinderella. She had a kind and gentle nature but lived a very difficult life. After her father passed away, Cinderella was left in the care of her wicked stepmother and two stepsisters, who treated her like a servant and made her do all the household chores.\n" +
                "\n" +
                "Cinderella's life was filled with hardship and misery, but she always remained good-hearted. She found solace in the company of the little birds and mice that lived in the house, and they became her only friends.\n" +
                "\n" +
                "One day, an invitation arrived at the house. The king was hosting a grand royal ball in honor of his son, the prince, who was seeking a bride. Cinderella's stepmother and stepsisters were delighted, but they had no intention of allowing Cinderella to attend the ball. They insisted that she stay home and help them prepare for the event.\n" +
                "\n" +
                "As her stepmother and stepsisters left for the ball, Cinderella couldn't help but cry. But in her distress, something magical happened. Her tears caught the attention of her fairy godmother, who appeared and promised to help Cinderella attend the ball. With a wave of her wand, the fairy godmother transformed Cinderella's tattered clothes into a beautiful gown, her worn-out shoes into sparkling glass slippers, and even turned a pumpkin into a magnificent carriage, complete with horses.\n" +
                "\n" +
                "Cinderella was astounded by the magical transformation and set off for the royal ball with a warning from her fairy godmother to leave the palace by midnight when the spell would break.\n" +
                "\n" +
                "At the ball, Cinderella captivated everyone with her beauty and grace, especially the prince. They danced the night away, and Cinderella was happier than she had ever been. But as the clock struck midnight, she remembered her fairy godmother's warning. She rushed from the palace, leaving behind one of her glass slippers.\n" +
                "\n" +
                "The prince was determined to find the mysterious girl who had stolen his heart. He searched the kingdom, and when he arrived at Cinderella's home, her stepsisters tried to fit into the glass slipper, but it was too small for them. When Cinderella's turn came, the slipper fit perfectly. The prince recognized her, and they were joyfully reunited.\n" +
                "\n" +
                "Cinderella married the prince, and they lived happily ever after, leaving her life of hardship behind. The moral of the story is that kindness and goodness are rewarded, even in the face of cruelty and injustice.");
        textContexts.add("In a faraway corner of the universe, there was a tiny asteroid named \"B-612,\" and on this asteroid lived a little prince. He had a very special rose that he cared for, and he cherished her deeply. However, he felt a bit lonely because the rose was a bit vain and demanding.\n" +
                "\n" +
                "To find answers to his questions and to explore the universe, the little prince decided to leave his asteroid. He embarked on a journey that led him to various asteroids, each inhabited by a unique character, including a king who wanted to rule everything, a vain man who wanted endless compliments, a drunkard who drank to forget his shame, and a lamplighter who dutifully lit and extinguished a lamp every minute, even though his asteroid spun too quickly.\n" +
                "\n" +
                "As he continued his travels, the little prince visited Earth, where he encountered a pilot who had crash-landed in the Sahara Desert. The pilot listened to the little prince's story and learned about the rose, the fox, and the other characters he had met along the way.\n" +
                "\n" +
                "One of the most important encounters for the little prince was with a fox, who taught him valuable lessons about friendship and love. The fox's secret was simple but profound: \"On ne voit bien qu'avec le cœur\" (One sees clearly only with the heart). The little prince realized that he had to tame the fox to truly understand the essence of those words.\n" +
                "\n" +
                "Despite the wonderful friendships he formed on Earth, the little prince missed his asteroid and his special rose. He met a snake who could help him leave Earth, but he faced a difficult choice. In the end, the little prince chose to return to his asteroid and his beloved rose, even though it meant leaving behind the friends he had made on Earth.");
        textContexts.add("Once upon a time, in the beautiful island of Motunui, there lived a young girl named Moana. Moana loved her island, but she knew that something was wrong. The coconuts were no longer sweet, and the fish were disappearing. The island's heart, a special stone called Te Fiti's heart, had been stolen by the demigod Maui, and this was causing the trouble.\n" +
                "\n" +
                "Moana's grandmother, Tala, had always encouraged Moana to listen to her heart and go on an adventure to save their island. Tala knew that Moana was chosen by the ocean to restore the heart of Te Fiti.\n" +
                "\n" +
                "Moana was scared, but she decided to be brave. She set out on a journey to find Maui, the one who took the heart, and make him return it. With the ocean's help, Moana found Maui, but he was a bit tricky and didn't want to help at first.\n" +
                "\n" +
                "Together with Maui, Moana faced many challenges, including pirates and a fiery monster called Te Kā. But she was determined to save her people.\n" +
                "\n" +
                "As they got to know each other, Maui and Moana became friends, and Maui agreed to help. They restored the heart of Te Fiti and brought life back to the ocean and their island.\n" +
                "\n" +
                "Moana returned to Motunui as a hero, and her people, thanks to her courage, could once again enjoy the sweetness of coconuts and the abundance of fish.\n" +
                "\n" +
                "Moana showed that being brave and following your heart can help you overcome challenges and make the world a better place.\n" +
                "\n" +
                "And so, the people of Motunui lived in harmony with nature, thanks to the courage and love of their hero, Moana.\n" +
                "\n");
        textContexts.add("Paddington Bear is a lovable bear who comes from \"Darkest Peru.\" He's named after the train station where he was found by the Brown family in London. Paddington is a very polite and friendly bear, but he's not like other bears because he can talk and loves marmalade sandwiches.\n" +
                "\n" +
                "Paddington was sent to London by his Aunt Lucy, who couldn't take care of him anymore. He arrived with a suitcase and a label that said, \"Please look after this bear. Thank you.\" The Browns found him at the train station and decided to take him home.\n" +
                "\n" +
                "Paddington quickly became a part of the Brown family, and they showed him the sights of London, including the famous Buckingham Palace. He got into all sorts of funny and sweet adventures, often because he didn't understand everything about the human world.\n" +
                "\n" +
                "One of his favorite things to do was to visit Mr. Gruber's antique shop and listen to his stories. He was also known for causing a bit of chaos, like when he flooded the bathroom while trying to take a bath.\n" +
                "\n" +
                "But no matter what happened, Paddington's kind and loving heart always shone through. He made friends wherever he went, and he touched the hearts of everyone he met.");
        textContexts.add("Once upon a time, in a faraway kingdom, there lived a kind and beautiful princess named Snow White. She had fair skin, ebony hair, and lips as red as a rose, which is why she was named Snow White. But her stepmother, the Queen, was wicked and jealous of Snow White's beauty. She wanted to be the fairest in the land and couldn't stand the thought of Snow White being more beautiful than her.\n" +
                "\n" +
                "The Queen ordered a huntsman to take Snow White deep into the forest and get rid of her. However, the huntsman couldn't bring himself to harm the princess and let her go. Snow White found herself in the dark, scary forest, but she was brave and soon came across a little cottage.\n" +
                "\n" +
                "Inside the cottage, she found it belonged to seven tiny miners who were named Grumpy, Happy, Sleepy, Dopey, Sneezy, Bashful, and Doc. They were surprised to see her but welcomed her with open arms when they learned about her plight. Snow White decided to stay with them, and they all became friends.\n" +
                "\n" +
                "Meanwhile, back at the castle, the Queen discovered that Snow White was still alive and decided to take matters into her own hands. She used dark magic to transform herself into an old hag and created a poisoned apple. The apple would put anyone who took a bite into a deep sleep, only to be awakened by true love's kiss.\n" +
                "\n" +
                "The wicked Queen offered the poisoned apple to Snow White, who took a bite and fell into a deep sleep. The dwarfs arrived in time to chase the Queen away and protect Snow White. They placed her in a glass coffin in the forest.\n" +
                "\n" +
                "One day, a handsome prince arrived and fell in love with the sleeping princess. He kissed her, and Snow White woke up. They were overjoyed to be reunited, and the prince took Snow White to his castle. They lived happily ever after.");
        textContexts.add("Once upon a time, there was a magical boy named Peter Pan. Peter didn't grow up, and he lived in a faraway place called Neverland. In Neverland, you could fly, and you could have amazing adventures every day.\n" +
                "\n" +
                "One night, while Wendy, John, and Michael were sleeping in their nursery in London, Peter Pan and his fairy friend Tinker Bell visited. They sprinkled some magical fairy dust on the children, and in an instant, they could fly!\n" +
                "\n" +
                "Peter, Wendy, John, Michael, and Tinker Bell flew off to Neverland. There, they met the Lost Boys, a group of kids who, like Peter, never grew up. They had all sorts of exciting adventures, like fighting pirates led by a funny but not-so-nice Captain Hook.\n" +
                "\n" +
                "In Neverland, they visited a Native American tribe led by Tiger Lily, swam with the mermaids in Mermaid Lagoon, and even made friends with a crocodile who liked to follow Captain Hook around.\n" +
                "\n" +
                "As much fun as they had, Wendy, John, and Michael started to miss their home and their family. They realized that they needed to go back to London. Peter was sad to see them leave, but he knew they had to grow up.\n" +
                "\n" +
                "The children returned to London, and when they looked out their nursery window, they could see Peter Pan flying by, having his own adventures.\n" +
                "\n" +
                "The story of Peter Pan teaches us that while growing up is a part of life, it's essential to keep our sense of imagination and fun alive. Peter Pan will always be out there, having wonderful adventures, and maybe he'll visit your dreams too.\n" +
                "\n" +
                "And so, they all lived happily ever after, remembering the magic of Neverland.\n" +
                "\n");
        textContexts.add("Once upon a time, there lived a mischievous little rabbit named Peter Rabbit. He was a fluffy, white bunny with a blue jacket, and he lived with his family in a cozy burrow under a big fir tree.\n" +
                "\n" +
                "Peter was known for his curiosity and his love of adventure. His mother often warned him to stay out of Mr. McGregor's garden because it was a dangerous place for a rabbit. But Peter was a very naughty bunny and didn't always listen.\n" +
                "\n" +
                "One day, when his mother was away, Peter couldn't resist the temptation to sneak into Mr. McGregor's garden to steal some of the delicious vegetables. He squeezed under the garden gate and began to munch on lettuce and carrots.\n" +
                "\n" +
                "However, Peter's adventure took a dangerous turn when Mr. McGregor spotted him in the garden. Peter panicked and ran around the garden, trying to escape. He lost his jacket and his shoes in the process, but he eventually managed to slip away and return to his burrow.\n" +
                "\n" +
                "When Peter got home, he was quite tired, and his mother scolded him for being so naughty. She was relieved that he had come back safely, and she gave him a nice cup of chamomile tea.\n" +
                "\n" +
                "Peter learned his lesson and decided to be a good little bunny from then on, staying out of Mr. McGregor's garden. He realized that there's no place like home, and he was grateful for his loving family.\n" +
                "\n" +
                "And so, Peter Rabbit and his family lived happily ever after, safe and sound in their cozy burrow.\n" +
                "\n");
        textContexts.add("Once upon a time, there was a kind and clever woodcarver named Geppetto. One day, he carved a wooden puppet and named him Pinocchio. To Geppetto's surprise, the puppet came to life! Pinocchio could talk, walk, and move like a real boy, but he was made of wood.\n" +
                "\n" +
                "Pinocchio was full of curiosity and wanted to explore the world, but he often got into trouble because he didn't always make good choices. His nose had a magical quality: it would grow longer when he told a lie.\n" +
                "\n" +
                "One day, a kind fairy noticed Pinocchio and saw his good heart. She gave him a second chance to become a real boy but warned that he needed to prove himself brave, truthful, and unselfish.\n" +
                "\n" +
                "On his adventures, Pinocchio met a talking cricket who gave him wise advice, but he didn't always listen. He was lured by bad companions, including a sly fox and a cunning cat, who led him astray.\n" +
                "\n" +
                "Pinocchio's journey was filled with challenges and dangers. He was captured by an evil puppeteer who wanted to make him perform in a puppet show. However, he managed to escape with the help of the Blue Fairy.\n" +
                "\n" +
                "With each adventure, Pinocchio learned valuable lessons about honesty, kindness, and responsibility. He even saved Geppetto from the belly of a giant whale.\n" +
                "\n" +
                "As Pinocchio proved that he could be brave, truthful, and unselfish, the Blue Fairy transformed him into a real, live boy. Geppetto was overjoyed to have his beloved Pinocchio become a real son.\n" +
                "\n" +
                "And so, Pinocchio and Geppetto lived happily ever after, with Pinocchio having become a good and honest boy.\n" +
                "\n");
        textContexts.add("Ponyo on the Cliff by the Sea is a delightful and magical story about a little fish named Ponyo and a young boy named Sosuke.\n" +
                "\n" +
                "Ponyo lives underwater with her father, Fujimoto, who is a scientist. One day, while exploring the ocean, Ponyo gets trapped in a glass jar and is rescued by Sosuke, a kind and brave boy who lives on the seaside cliff.\n" +
                "\n" +
                "Sosuke takes care of Ponyo and keeps her in a bucket of water. But something magical happens. Ponyo wants to become human so that she can be with Sosuke, and her wish comes true! She transforms into a little girl with a fishy tail.\n" +
                "\n" +
                "However, this magical change causes a problem for the balance of nature. Her father, Fujimoto, is worried and wants to bring her back to the sea. But Ponyo loves Sosuke and wants to stay with him.\n" +
                "\n" +
                "A big storm approaches, and Ponyo's magical powers cause the ocean to overflow. Sosuke's mother is worried, but Ponyo's love and determination help her save the day. She uses her magic to calm the storm and restore the balance of nature.\n" +
                "\n" +
                "In the end, Ponyo becomes a real little girl and can be with Sosuke. They enjoy their adventures together, exploring the underwater world and the beauty of the sea.");
        textContexts.add("Once upon a time, in a magnificent kingdom, there lived a prince who was searching for a true princess to be his wife. He traveled to many lands, met many noble ladies, but he couldn't find a princess who felt just right. He wanted someone who possessed a certain delicacy and sensitivity, qualities that only a true princess would have.\n" +
                "\n" +
                "One stormy night, as lightning streaked across the sky and the rain poured down in torrents, a young woman appeared at the castle gates. She was drenched and her clothes were in disarray. She told the guards that she was a princess in need of shelter from the fierce storm.\n" +
                "\n" +
                "The prince's mother, the wise queen, received the young woman. She saw that the stranger was exhausted and wet from head to toe. The queen thought this was an opportunity to find out if she was indeed a true princess.\n" +
                "\n" +
                "To test her, the queen prepared a special bed for her guest. They took twenty soft and fluffy mattresses and placed them on top of each other, followed by twenty featherbeds. It was the most luxurious and comfortable bed that anyone could imagine.\n" +
                "\n" +
                "However, the queen also decided to add a unique element to the bed to test the princess's sensitivity. She placed a tiny, hard pea right in the middle of all those mattresses and featherbeds. It was so small that it was barely visible.\n" +
                "\n" +
                "The young woman was shown to her room, where she changed into dry clothing and climbed into the opulent bed. But throughout the night, she couldn't find a comfortable position to sleep. She kept tossing and turning, trying to escape a lump that seemed to be poking her. By morning, she was exhausted.\n" +
                "\n" +
                "The queen and the prince asked her how she had slept. She replied, \"Oh, it was dreadful! I couldn't sleep at all. I felt something hard in the bed, and it left me bruised all over.\"\n" +
                "\n" +
                "The prince and the queen knew then that they had found a real princess. Only a true princess could be so delicate and sensitive that she could feel a tiny pea hidden under such an extravagant pile of mattresses and featherbeds.\n" +
                "\n" +
                "The prince was overjoyed. He knew he had found the princess he had been searching for. He asked her to be his wife, and she happily agreed.\n" +
                "\n" +
                "From that day on, the young woman was known as the Princess of the Pea. She and the prince were married in a grand ceremony, and they lived happily ever after in the palace.");
        textContexts.add("Once upon a time, in a faraway land, there lived a clever and charming cat named Puss in Boots. He was no ordinary cat; he was also an excellent and cunning trickster.\n" +
                "\n" +
                "Puss in Boots belonged to a kind but poor miller. When the miller passed away, he left his three sons with his only possessions – his mill, a donkey, and Puss.\n" +
                "\n" +
                "Puss, knowing that his master and his master's sons were struggling, came up with a clever plan to help them. He asked the youngest son to give him a pair of boots and a sack.\n" +
                "\n" +
                "With his new boots on and the sack slung over his shoulder, Puss set off for the royal palace. Once there, he managed to catch a rabbit, which he presented to the king as a gift from his master, the Marquis of Carabas (even though he wasn't a marquis at all!).\n" +
                "\n" +
                "Puss continued to visit the palace and present more impressive gifts, including game birds and other treasures, all from the imaginary Marquis of Carabas. The king was delighted and decided that this Marquis would make a suitable husband for his daughter, the beautiful princess.\n" +
                "\n" +
                "Puss in Boots knew that his next plan was the most daring. He went to the castle of a fearsome ogre and used his quick thinking to convince the ogre to transform into a mouse. Then, Puss caught the ogre-mouse and presented it to the king as yet another gift.\n" +
                "\n" +
                "The king was even more impressed and celebrated the upcoming marriage between his daughter and the Marquis of Carabas. The young miller's son, thanks to Puss in Boots' clever schemes, became a marquis and wed the princess, leading to a happily ever after.\n" +
                "\n" +
                "Puss in Boots was not just clever but loyal as well. He helped his master and his master's son rise to greatness, and they all lived happily together.");
        textContexts.add("Once upon a time, in a land far away, there was a beautiful girl named Rapunzel. She had long, golden hair that seemed to go on forever. Rapunzel was special because she was locked in a tall tower in the middle of a deep, dark forest.\n" +
                "\n" +
                "Rapunzel's tower had no doors or stairs, and the only way in or out was through a window at the very top. She lived there because an evil witch had locked her in the tower when she was just a baby.\n" +
                "\n" +
                "The witch visited Rapunzel in the tower, using Rapunzel's hair like a ladder to climb up. Rapunzel didn't mind her life in the tower, but she often wondered about the world outside.\n" +
                "\n" +
                "One day, a brave and kind prince happened to hear Rapunzel's beautiful singing while passing through the forest. He followed the sound and discovered the hidden tower.\n" +
                "\n" +
                "The prince saw Rapunzel and asked her to let down her long hair. She did, and he climbed up to meet her. They talked, and they liked each other very much. The prince decided that he wanted to rescue Rapunzel and take her away from the tower.\n" +
                "\n" +
                "Rapunzel agreed, but they had to be very careful because the witch would be very angry if she found out. The prince visited Rapunzel secretly many times, and they fell in love.\n" +
                "\n" +
                "One day, the witch did find out and, in her anger, she cut off Rapunzel's beautiful hair and banished her to the wilderness. The prince returned to the tower, only to find the evil witch waiting for him. She pushed him from the tower, and he was blinded by thorns below.\n" +
                "\n" +
                "For a long time, the prince wandered in sadness, but he never gave up hope. He roamed the forest, searching for Rapunzel.\n" +
                "\n" +
                "Eventually, he heard her voice again, and he followed it to the wilderness where she was living. When they were reunited, Rapunzel's tears of happiness fell onto the prince's eyes, and he miraculously regained his sight.\n" +
                "\n" +
                "The prince took Rapunzel back to his kingdom, and they lived happily ever after.");
        textContexts.add("Once upon a time, in a small coastal village, there lived a young boy named William. The village was known for its shimmering white beaches and the clear blue waters of the sea. But it was also known for a legendary treasure called \"The Black Pearl.\"\n" +
                "\n" +
                "The story of The Black Pearl had been passed down through generations. It was said to be a pearl so large and so black that it shone like a piece of the night sky in the palm of your hand. Many had tried to find it, but none had succeeded.\n" +
                "\n" +
                "One warm summer's day, as the sun began to set over the horizon, William decided to go on an adventure. He believed he could be the one to discover The Black Pearl and bring prosperity to his village.\n" +
                "\n" +
                "He gathered his things, kissed his mother and father, and set off with a small boat onto the sparkling sea. William had heard whispers of The Black Pearl's location, hidden on an island far from the village.\n" +
                "\n" +
                "As he sailed, the waves rocked his boat gently, and the salty breeze filled his lungs. He sang songs his mother had taught him, and they mingled with the cries of seagulls. Day turned into night, and the stars above guided his way.\n" +
                "\n" +
                "Days turned into weeks, and William's determination never waned. He encountered dolphins and playful turtles, and they seemed to lead him in the right direction.\n" +
                "\n" +
                "Finally, after a long journey, he reached the mysterious island, where the jungle was so dense that it blocked the sun. William's heart raced as he followed the whispers of the wind, the riddles of the leaves, and the secrets of the birds.\n" +
                "\n" +
                "After much searching, he came upon a hidden cave. Inside, the air was cool, and the walls glistened with an otherworldly light. And there, resting on a bed of shimmering sand, lay The Black Pearl, as magnificent and breathtaking as the legends described.\n" +
                "\n" +
                "With great care, William held the pearl in his hands, feeling its magic and wonder. He knew he had found the greatest treasure of all.\n" +
                "\n" +
                "As he returned to the village, the pearl radiated its enchanting glow, lighting up the entire sea and guiding him home. The villagers marveled at the sight of The Black Pearl and the bravery of young William.\n" +
                "\n" +
                "The village flourished with the newfound prosperity brought by The Black Pearl, but they knew that the real treasure was the young boy who had shown them the importance of courage and determination.\n" +
                "\n" +
                "And so, the story of \"The Black Pearl\" became a cherished tale in the village, a reminder that even the greatest of treasures is found within the hearts of those who dare to dream and believe in themselves.");
        textContexts.add("In the charming and magical Smurf Village, where the adorable blue creatures known as The Smurfs lived, every day was filled with fun and excitement. On a bright and sunny morning, the Smurfs decided to have a special picnic in the heart of the enchanting forest. They were all buzzing with excitement, packing their favorite foods, games, and musical instruments.\n" +
                "\n" +
                "As they ventured into the forest, Papa Smurf, their wise and caring leader, gathered them together for a quick reminder about the importance of being good caretakers of the environment. He spoke about not littering, cleaning up after themselves, and showing respect to the forest they called home.\n" +
                "\n" +
                "The Smurfs spread out their picnic blankets and enjoyed a day of laughter, delicious food, and entertaining games. They played tag, had a sack race, and even danced to the tunes of Smurfette's flute.\n" +
                "\n" +
                "As they were enjoying their picnic, one of the Smurfs, Clumsy, noticed a tiny bird perched on a nearby tree, looking worried. The bird's nest had fallen, and its eggs were scattered around the forest floor. The Smurfs, with their kind hearts, quickly rushed to the little bird's aid.\n" +
                "\n" +
                "They collected the eggs with great care and placed them gently back in the nest. The bird parents were overjoyed and chirped happily, expressing their gratitude for the Smurfs' help. In return, they decided to assist the Smurfs.\n" +
                "\n" +
                "The bird family used their quick wings to help the Smurfs clean up the picnic area, swooping in to take away any stray crumbs and tidying up the forest floor. The Smurfs watched in amazement and gratitude as the bird family worked their magic.\n" +
                "\n" +
                "Papa Smurf took this opportunity to remind the Smurfs about the value of kindness and helping those in need. He explained that even small acts of kindness could have a significant impact on the world around us. The Smurfs listened attentively, appreciating the lesson they had learned.\n" +
                "\n" +
                "As the sun began to set, the Smurfs made their way back to their village, their hearts filled with the joy of helping the bird family and a deeper appreciation for their beautiful forest home. They shared stories and laughter on their journey home, knowing that they had made a difference in the world that day.");
        textContexts.add("The Adventures of Tom Sawyer");
        textContexts.add("The wolf huffed and puffed and blew the straw house down! The first little pig was scared but managed to escape and ran to his brother's house, the one made of sticks.\n" +
                "\n" +
                "The wolf followed him to the second pig's house and said, \"Little pigs, little pigs, let me come in.\"\n" +
                "\n" +
                "The second little pig, a bit smarter than the first, replied, \"Not by the hair on our chinny chin chins.\"\n" +
                "\n" +
                "The wolf huffed and puffed again, and this time, he blew down the stick house. The two little pigs ran to their brother's house, the one made of bricks, for safety.\n" +
                "\n" +
                "The wolf was determined to catch them. He came to the brick house and said, \"Little pigs, little pigs, let me come in.\"\n" +
                "\n" +
                "But the third little pig, who was very clever, said, \"Not by the hair on our chinny chin chins.\"\n" +
                "\n" +
                "The wolf huffed and puffed with all his might, but no matter how hard he tried, he couldn't blow down the brick house. The three little pigs were safe and sound inside.\n" +
                "\n" +
                "The wolf realized he couldn't get to the pigs by blowing the house down, so he tried to sneak down the chimney. But the three little pigs were ready. They had a pot of boiling water waiting in the fireplace. When the wolf climbed down the chimney, he landed right in the pot, and the three little pigs put the lid on. The wolf couldn't escape.\n" +
                "\n" +
                "The three little pigs danced and sang, happy that they had outsmarted the big, bad wolf. They knew that building a strong and sturdy house was important, and they were safe in their brick house.\n" +
                "\n" +
                "And so, the three little pigs lived happily ever after, knowing that hard work and cleverness could protect them from any danger.");
        textContexts.add("Once upon a time, in a charming little house in London, there lived a young couple named Pongo and Perdita. They were Dalmatians, a breed of dogs known for their distinctive spots.\n" +
                "\n" +
                "Pongo and Perdita were deeply in love and shared their home with their loving owners, Roger and Anita. Life was wonderful until one day when their peaceful existence was disrupted by the wicked Cruella de Vil. Cruella was an eccentric fashion-obsessed woman who had a sinister plan to make a fur coat out of Dalmatian puppies.\n" +
                "\n" +
                "When Perdita gave birth to a litter of 15 adorable Dalmatian puppies, Cruella couldn't resist, and she kidnapped them along with many other Dalmatian puppies from the neighborhood. This sparked a desperate journey for Pongo and Perdita to rescue their puppies.\n" +
                "\n" +
                "Pongo and Perdita embarked on an epic adventure to rescue their beloved puppies from Cruella's clutches. Along the way, they received help from various animal friends, including a brave tabby cat named Sergeant Tibbs.\n" +
                "\n" +
                "The determined Dalmatian parents, along with their newfound friends, journeyed across the English countryside to reach Cruella's grand mansion. There, they discovered the horrifying truth about her cruel intentions and the fate of their puppies.\n" +
                "\n" +
                "With the help of their animal allies and some quick thinking, Pongo and Perdita managed to rescue not only their own 15 puppies but also the additional 84 Dalmatian puppies Cruella had kidnapped. They all returned to their loving owners, Roger and Anita, creating a harmonious and joyful home once again.\n" +
                "\n" +
                "The heartwarming story of \"101 Dalmatians\" teaches us about the power of love, courage, and teamwork in the face of adversity. It's a tale of family, friendship, and the triumph of good over evil.");

    }
    public void addIntors(){
        textIntros.add("Aladdin is a captivating tale of magic, wishes, and adventure in the enchanting city of Agrabah.");
        textIntros.add("Ali Baba and the Forty Thieves unfolds a thrilling tale of hidden treasure, secret passageways, and a clever hero in the heart of the Arabian Nights.");
        textIntros.add("Alice in Wonderland invites readers into a whimsical and nonsensical world where nothing is quite as it seems");
        textIntros.add("Goldilocks tells the story of a curious young girl who ventures into a bear's home and encounters surprises beyond her imagination.");
        textIntros.add("Bambi, a timeless story, follows the life of a young deer in the enchanting forest.");
        textIntros.add("In the tale of \"Beremen's Musicians,\" a clever cat leads a band of misfit animals to outsmart a greedy innkeeper and find their fortune.");
        textIntros.add("\"In The Ugly Duckling,\" a swan is mistakenly raised among ducks, enduring ridicule until discovering its true identity and beauty.");
        textIntros.add("In the story of \"The Little Mermaid,\" a young mermaid sacrifices her voice to pursue a human prince, facing trials and transformation for true love.");
        textIntros.add("\"In The Nutcracker,\" a girl's beloved nutcracker comes to life, leading her to a magical world of adventure and wonder during Christmas Eve.");
        textIntros.add("In \"How the Grinch Stole Christmas,\" a grouchy Grinch learns the true meaning of the holiday season when his heart grows three sizes after attempting to ruin Christmas for a town.");
        textIntros.add("In \"Hansel and Gretel,\" two siblings outsmart a wicked witch who lures them into her gingerbread house, ensuring their escape and the witch's downfall.");
        textIntros.add("\"Heidi\" follows the journey of a young Swiss orphan as she brings joy and healing to the lives of her grumpy grandfather and those in her Alpine village.");
        textIntros.add("\"In Jack ands the Beanstalk,\" a boy named Jack trades a cow for magic beans, leading to a giant beanstalk, adventures in the sky, and outsmarting a giant.");
        textIntros.add("\"Keloglan\" is a popular Turkish folk character, known for his wit and resourcefulness, often embarking on various adventures and solving problems.");
        textIntros.add("\"The Little Match Girl\" tells the poignant story of a poor girl who lights matches to see warm visions on a cold New Year's Eve, ultimately passing away, but finding comfort in a heavenly vision.");
        textIntros.add("In \"Little Red Riding Hood,\" a young girl takes a trip through the forest to visit her grandmother, but encounters a cunning wolf along the way.");
        textIntros.add("\"The Emperor's New Clothes\" is a tale about an emperor who's conned into wearing \"invisible\" clothes by clever swindlers, until a child points out the truth.");
        textIntros.add("In \"Cinderella,\" a kind-hearted young woman, mistreated by her stepmother and stepsisters, is magically transformed and meets her prince at a royal ball.");
        textIntros.add("\"The Little Prince\" tells the story of a young prince who travels to different planets, learning life lessons and forming an unusual friendship with a pilot stranded in the desert.");
        textIntros.add("In \"Moana,\" a spirited Polynesian girl sets sail across the ocean to save her island, accompanied by the demigod Maui, discovering her true destiny.");
        textIntros.add("\"Paddington\" follows the adventures of a friendly Peruvian bear who arrives in London, charming the Brown family and getting into comical escapades.");
        textIntros.add("In \"Snow White,\" a jealous queen orders the death of her stepdaughter, Snow White, who finds refuge with seven dwarfs, ultimately awakened by a prince's kiss.");
        textIntros.add("\"Peter Pan\" features a boy who never grows up, leading children to Neverland, where they confront pirates, Indians, and fairies, with Peter Pan's arch-nemesis being Captain Hook.");
        textIntros.add("\"Peter Rabbit\" follows the mischievous adventures of a clever rabbit named Peter as he outwits Mr. McGregor in his pursuit of garden vegetables.");
        textIntros.add("\"Pinocchio\" is the story of a wooden puppet brought to life, who learns the value of honesty and kindness on his quest to become a real boy.");
        textIntros.add("\"Ponyo\" tells the enchanting tale of a fish who wants to become human, forming a deep bond with a young boy and embarking on a magical adventure.");
        textIntros.add("\"In The Princess and the Pea,\" a sensitive princess proves her royal heritage by detecting a pea hidden beneath a pile of mattresses, leading to a royal wedding.");
        textIntros.add("\"Puss in Boots\" narrates the adventures of a clever and dashing cat who uses wit and charm to win over a king, while also aiding his owner, a poor miller's son, in achieving great wealth.");
        textIntros.add("\n" +
                "In \"Rapunzel,\" a long-haired princess is locked in a tower by a wicked witch until a prince helps her escape, showcasing her magical hair's power.");
        textIntros.add("The \"Black Pearl Legend\" is a mythical story of a cursed pirate ship, the Black Pearl, with a crew of the undead, setting sail to find cursed Aztec gold and encountering various supernatural challenges.");
        textIntros.add("\"The Smurfs\" are small, blue, and cheerful creatures who live in a magical forest. They have various adventures while outsmarting the evil wizard Gargamel, with their leader, Papa Smurf, guiding them through whimsical journeys." +
                "\n" +
                "\n" +
                "\n" +
                "\n");
        textIntros.add("\"Tom Sawyer\" is an adventurous tale about a mischievous boy and his friends in a small Mississippi River town.");
        textIntros.add("\"The Three Little Pigs\" is a classic tale about three pigs who build houses of straw, sticks, and bricks, outsmarting the Big Bad Wolf who tries to blow their houses down.");
        textIntros.add("\"Twenty-One Dalmatians\" features the daring rescue of 99 Dalmatian puppies from the evil Cruella de Vil, known for her obsession with fur coats.");
    }
    public void setTextHeaders(){
        textHeaders.add("Alaaddin And Genie");
        textHeaders.add("Ali Baba and 40 Soldiers");
        textHeaders.add("Alice In Wonderland");
        textHeaders.add("Goldilocks");
        textHeaders.add("Bambi In The Forest");
        textHeaders.add("Bremen Town Musicians");
        textHeaders.add("The Ugly Duckling");
        textHeaders.add("The Little Mermaid");
        textHeaders.add("The Nutcracker");
        textHeaders.add("How the Grinch Stole Christmas");
        textHeaders.add("Hansel and Gretel");
        textHeaders.add("Heidi");
        textHeaders.add("Jack and the Beanstalk");
        textHeaders.add("Adventure of Keloglan");
        textHeaders.add("The Little Matchstick Girl");
        textHeaders.add("Little Red Riding Hood");
        textHeaders.add("The Emperor's New Clothes");
        textHeaders.add("Cinderella");
        textHeaders.add("The Little Prince");
        textHeaders.add("Moana");
        textHeaders.add("Paddington Bear");
        textHeaders.add("Snow White and the Seven Dwarfs");
        textHeaders.add("Peter Pan");
        textHeaders.add("Peter Rabbit");
        textHeaders.add("Pinocchio");
        textHeaders.add("Ponyo on the Cliff by the Sea");
        textHeaders.add("The Princess and the Pea");
        textHeaders.add("Puss in Boots");
        textHeaders.add("Rapunzel");
        textHeaders.add("The Black Pearl");
        textHeaders.add("The Smurfs");
        textHeaders.add("The Adventures of Tom Sawyer");
        textHeaders.add("The Three Little Pigs");
        textHeaders.add("101 Dalmatians");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }
}