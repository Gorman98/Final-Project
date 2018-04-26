package ser210.quinnipiac.edu.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import org.json.JSONException;


public class SearchFragment extends Fragment {

    private RelativeLayout searchFrag;
    static String key = "d6cc0d2a46052f4e3fd2b5dcdef40db0";
    static String URL = "";
    private EditText input;
    private String search;
    private String genre;
    private Button bttn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_search, container, false);
        searchFrag = (RelativeLayout) v.findViewById(R.id.searchFragment);
        searchFrag.setBackgroundColor(getResources().getColor(MainActivity.color));

        ImageView genreBanner = (ImageView) v.findViewById(R.id.genreBanner);

        input = (EditText) v.findViewById(R.id.input);

        genre = "empty";
        if(SearchActivity.genreSelected.equals("Anime")) {
            //set banner and api for anime
            genreBanner.setImageResource(R.drawable.animebanner);
            genre = "anime";
        }
        else if(SearchActivity.genreSelected.equals("Movies")) {
            //st banner and api for movies
            genreBanner.setImageResource(R.drawable.moviebanner);
            genre = "movie";
        }
        else if(SearchActivity.genreSelected.equals("TV")) {
            //set banner and api for tv
            genreBanner.setImageResource(R.drawable.tvbanner);
            genre = "tv";

        }
        else  {
            //set banner and api for video games
            genreBanner.setImageResource(R.drawable.videogamebanner);
            genre = "game";
        }

        bttn = (Button) v.findViewById(R.id.searchBttn);
        bttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(genre == "anime") {
                    search = input.getText().toString().trim();
                    search.replaceAll(" ", "+");
                    URL="https://myanimelist.net/api/anime/search.xml?q=" + search;
                } else if (genre == "movie") {
                    URL = "https://api.themoviedb.org/3/movie/550?api_key=ddea89b7d05ee63353966311f2d7e65f";
                    Log.d("test", "asd");
                    check();
                } else if (genre == "tv") {
                    URL = "https://api.themoviedb.org/3/tv/550?api_key=ddea89b7d05ee63353966311f2d7e65f";
                } else if (genre == "games") {
                    URL = "https://api-endpoint.igdb.com/games/" + "?mashap-key=d6cc0d2a46052f4e3fd2b5dcdef40db0";
                }
            }
        });

        System.out.println("GENRE " + SearchActivity.genreSelected);

        return v;
    }


    public void result(String i, String i2){
        Intent intent = new Intent(getActivity(), ReviewActivity.class);
        intent.putExtra("title", i);
        intent.putExtra("overview", i2);
        startActivity(intent);
    }

    public void check(){
        try {
            Log.d("test", URL);
            new Worker(this).execute(URL);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}