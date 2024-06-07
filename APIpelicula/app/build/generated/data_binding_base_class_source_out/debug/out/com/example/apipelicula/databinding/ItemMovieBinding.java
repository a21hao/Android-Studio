// Generated by view binder compiler. Do not edit!
package com.example.apipelicula.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.apipelicula.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ItemMovieBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final ImageView movieImage;

  @NonNull
  public final TextView moviePoints;

  @NonNull
  public final TextView movieTitle;

  private ItemMovieBinding(@NonNull LinearLayout rootView, @NonNull ImageView movieImage,
      @NonNull TextView moviePoints, @NonNull TextView movieTitle) {
    this.rootView = rootView;
    this.movieImage = movieImage;
    this.moviePoints = moviePoints;
    this.movieTitle = movieTitle;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ItemMovieBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ItemMovieBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.item_movie, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ItemMovieBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.movieImage;
      ImageView movieImage = ViewBindings.findChildViewById(rootView, id);
      if (movieImage == null) {
        break missingId;
      }

      id = R.id.moviePoints;
      TextView moviePoints = ViewBindings.findChildViewById(rootView, id);
      if (moviePoints == null) {
        break missingId;
      }

      id = R.id.movieTitle;
      TextView movieTitle = ViewBindings.findChildViewById(rootView, id);
      if (movieTitle == null) {
        break missingId;
      }

      return new ItemMovieBinding((LinearLayout) rootView, movieImage, moviePoints, movieTitle);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}