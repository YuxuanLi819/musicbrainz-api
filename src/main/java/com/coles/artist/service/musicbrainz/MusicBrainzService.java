package com.coles.artist.service.musicbrainz;

import com.coles.artist.domain.Artist;
import com.coles.artist.exception.ArtistNotFoundException;
import com.coles.artist.exception.ServiceNotAvailableException;

public interface MusicBrainzService {

	Artist getMusicBrainzArtistDetails(String mbid) throws ArtistNotFoundException, ServiceNotAvailableException;

}
