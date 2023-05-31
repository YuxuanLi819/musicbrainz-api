package com.coles.artist.service.discogs;

import java.util.concurrent.CompletableFuture;

public interface DiscogsService {

	CompletableFuture<String> getArtistProfile(String artistId);
}
