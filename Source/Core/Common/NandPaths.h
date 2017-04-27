// Copyright 2008 Dolphin Emulator Project
// Licensed under GPLv2+
// Refer to the license.txt file included.

#pragma once

#include <string>
#include <utility>
#include <vector>

#include "Common/CommonTypes.h"

static const u64 TITLEID_SYSMENU = 0x0000000100000002;
static const std::string TITLEID_SYSMENU_STRING = "0000000100000002";

namespace Common
{
enum FromWhichRoot
{
  FROM_CONFIGURED_ROOT,  // not related to currently running game - use D_WIIROOT_IDX
  FROM_SESSION_ROOT,     // request from currently running game - use D_SESSION_WIIROOT_IDX
};

std::string RootUserPath(FromWhichRoot from);

// Returns /import/%08x/%08x. Intended for use by ES.
std::string GetImportTitlePath(u64 title_id, FromWhichRoot from = FROM_SESSION_ROOT);

std::string GetTicketFileName(u64 _titleID, FromWhichRoot from);
std::string GetTMDFileName(u64 _titleID, FromWhichRoot from);
std::string GetTitleDataPath(u64 _titleID, FromWhichRoot from);
std::string GetTitleContentPath(u64 _titleID, FromWhichRoot from);

// Escapes characters that are invalid or have special meanings in the host file system
std::string EscapeFileName(const std::string& filename);
// Escapes characters that are invalid or have special meanings in the host file system
std::string EscapePath(const std::string& path);
// Reverses escaping done by EscapeFileName
std::string UnescapeFileName(const std::string& filename);
}
