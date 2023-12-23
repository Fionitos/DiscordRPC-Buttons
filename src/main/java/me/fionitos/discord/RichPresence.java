package me.fionitos.discord;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public record RichPresence(@Nullable String state,
                           @Nullable String details,
                           @Nullable RichPresence.Timestamps timestamps,
                           @Nullable RichPresence.Assets assets,
                           @Nullable RichPresence.Party party,
                           @Nullable RichPresence.Secrets secrets,
                           @Nullable List<Button> buttons) {

    public record Button(@NotNull String label, @NotNull String url) {
    }

    public record Timestamps(@Nullable Long start, @Nullable Long end) {
    }

    public record Assets(@Nullable @SerializedName("large_image") String largeImage,
                         @Nullable @SerializedName("large_text") String largeText,
                         @Nullable @SerializedName("small_image") String smallImage,
                         @Nullable @SerializedName("small_text") String smallText) {
    }

    public record Party(@NotNull String id, int[] size) {
    }

    public record Secrets(@Nullable String join, @Nullable String spectate, @Nullable String match) {
    }

    public JsonObject toJson() {
        // Main
        JsonObject o = new JsonObject();

        if (details != null) o.addProperty("details", details);
        if (state != null) o.addProperty("state", state);

        // Assets
        if (assets != null) {
            JsonObject a = new JsonObject();

            if (assets.largeImage != null) a.addProperty("large_image", assets.largeImage);
            if (assets.largeText != null) a.addProperty("large_text", assets.largeText);
            if (assets.smallImage != null) a.addProperty("small_image", assets.smallImage);
            if (assets.smallText != null) a.addProperty("small_text", assets.smallText);

            o.add("assets", a);
        }

        // Timestamps
        if (timestamps != null) {
            JsonObject t = new JsonObject();

            if (timestamps.start != null) t.addProperty("start", timestamps.start);
            if (timestamps.end != null) t.addProperty("end", timestamps.end);

            o.add("timestamps", t);
        }

        //  Buttons
        if (buttons != null) {
            JsonArray b = new JsonArray();
            for (Button button : buttons) {
                JsonObject buttonObject = new JsonObject();
                buttonObject.addProperty("label", button.label);
                buttonObject.addProperty("url", button.url);
                b.add(buttonObject);
            }

            o.add("buttons", b);
        }

        return o;
    }


    public static class Builder {
        public String state = null;
        public String details = null;

        public Long start = null;
        public Long end = null;

        public String largeImage = null;
        public String largeText = null;
        public String smallImage = null;
        public String smallText = null;

        public String  id = null;
        public Integer size = null;
        public Integer max = null;

        public String join = null;
        public String spectate = null;
        public String match = null;

        public Map<String, String> buttons = null;

        public Builder setText(@Nullable String details, @Nullable String state) {
            this.state = state;
            this.details = details;
            return this;
        }

        public Builder setTimestamps(@Nullable Long start, @Nullable Long end) {
            this.start = start;
            this.end = end;
            return this;
        }

        public Builder setAssets(@Nullable String largeImage,
                                 @Nullable String largeText,
                                 @Nullable String smallImage,
                                 @Nullable String smallText) {
            this.largeImage = largeImage;
            this.largeText = largeText;
            this.smallImage = smallImage;
            this.smallText = smallText;
            return this;
        }

        public Builder setPartyInfo(@Nullable String id, int size, int max) {
            this.id = id;
            this.size = size;
            this.max = max;
            return this;
        }

        public Builder setSecrets(@Nullable String join, @Nullable String spectate, @Nullable String match) {
            this.join = join;
            this.spectate = spectate;
            this.match = match;
            return this;
        }

        public Builder setButtons(@NotNull Map<String, String> buttonsMap) {
            buttons = new HashMap<>(buttonsMap);;
            return this;
        }

        public RichPresence build() {
            var timestamps = new Timestamps(start, end);
            Assets assets = null;
            if ((largeImage != null && largeText != null) || (smallImage != null && smallText != null)) {
                if ((largeImage != null && (largeImage.isEmpty() || largeText.isEmpty()))
                        || (smallImage != null && (smallImage.isEmpty() || smallText.isEmpty()))) {
                    throw new IllegalArgumentException("RichPresence must not be built with empty image strings");
                }
                assets = new Assets(largeImage, largeText, smallImage, smallText);
            }

           Party party = null;
            if (id != null && size != null && max != null) {
                party = new Party(id, new int[]{size, max});
            }

            Secrets secrets = null;
            if (join != null || spectate != null || match != null) {
                secrets = new Secrets(join, spectate, match);
            }

            List<Button> buttons = null;
            if (this.buttons != null && !this.buttons.isEmpty()) {
                buttons = new ArrayList<>();
                for (Map.Entry<String, String> entry : this.buttons.entrySet()) {
                    buttons.add(new Button(entry.getKey(), entry.getValue()));
                }
            }

            return new RichPresence(state, details, timestamps, assets, party, secrets, buttons);
        }
    }
}
