package com.coles.artist.service.artist;

import java.util.concurrent.CompletableFuture;

import com.coles.artist.domain.Artist;
import com.coles.artist.exception.ArtistNotFoundException;
import com.coles.artist.exception.ServiceNotAvailableException;

public interface ArtistService {

	CompletableFuture<Artist> getArtistInfo(String mbid) throws ArtistNotFoundException, ServiceNotAvailableException;

}
