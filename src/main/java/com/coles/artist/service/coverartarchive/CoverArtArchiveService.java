package com.coles.artist.service.coverartarchive;

import java.util.concurrent.CompletableFuture;

public interface CoverArtArchiveService {

	CompletableFuture<String> getAlbumCoverArtImage(String releaseGroupMbid);
}
