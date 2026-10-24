const REFRESH_MS = 60_000;

const $ = (id) => document.getElementById(id);
const fmtBytes = (bytes) => {
  if (!Number.isFinite(bytes) || bytes < 0) return "—";
  const units = ["B", "KB", "MB", "GB", "TB"];
  let value = bytes, unit = 0;
  while (value >= 1024 && unit < units.length - 1) { value /= 1024; unit++; }
  return `${value.toFixed(unit >= 3 ? 2 : 1)} ${units[unit]}`;
};
const fmtUptime = (seconds) => {
  let s = Math.max(0, Math.floor(seconds || 0));
  const days = Math.floor(s / 86400); s %= 86400;
  const hours = Math.floor(s / 3600); s %= 3600;
  const minutes = Math.floor(s / 60);
  if (days) return `${days}d ${hours}h`;
  if (hours) return `${hours}h ${minutes}m`;
  return `${minutes}m`;
};
const safeText = (value) => String(value ?? "Unknown");

function setBar(id, percent) {
  $(id).style.width = `${Math.max(0, Math.min(100, Number(percent) || 0))}%`;
}
function setLoad(valueId, barId, value, cores) {
  $(valueId).textContent = Number(value || 0).toFixed(2);
  // Visual scale is relative to the logical CPU count, capped to avoid overflow.
  setBar(barId, (Number(value || 0) / Math.max(1, cores)) * 100);
}
function statusClass(status) {
  if (status === "CRITICAL") return "critical";
  if (status === "WARNING") return "warning";
  return "";
}
function renderDisks(disks) {
  const list = $("diskList");
  list.replaceChildren();
  if (!Array.isArray(disks) || disks.length === 0) {
    const empty = document.createElement("div");
    empty.className = "empty-state";
    empty.textContent = "No mounted filesystems with measurable capacity were found.";
    list.appendChild(empty);
    return;
  }
  disks.forEach((disk) => {
    const item = document.createElement("div");
    item.className = "disk-item";
    const title = document.createElement("div");
    title.className = "disk-title";
    const name = document.createElement("strong");
    name.textContent = `${safeText(disk.mount)} · ${safeText(disk.name)}`;
    const percent = document.createElement("span");
    const usedPercent = Number(disk.usedPercent) || 0;
    percent.textContent = `${usedPercent.toFixed(1)}%`;
    title.append(name, percent);

    const track = document.createElement("div");
    track.className = "disk-track";
    const fill = document.createElement("div");
    fill.className = `disk-fill ${usedPercent >= 90 ? "critical" : usedPercent >= 75 ? "warning" : ""}`;
    fill.style.width = `${Math.max(0, Math.min(100, usedPercent))}%`;
    track.appendChild(fill);

    const meta = document.createElement("div");
    meta.className = "disk-meta";
    const used = document.createElement("span");
    used.textContent = `${fmtBytes(disk.usedBytes)} used`;
    const total = document.createElement("span");
    total.textContent = `${fmtBytes(disk.totalBytes)} total`;
    meta.append(used, total);
    item.append(title, track, meta);
    list.appendChild(item);
  });
}
function render(data) {
  $("hostname").textContent = safeText(data.hostname);
  $("osName").textContent = safeText(data.operatingSystem);
  $("cpuValue").textContent = Number(data.cpuPercent || 0).toFixed(1);
  $("cpuCores").textContent = safeText(data.logicalCpuCores);
  setBar("cpuBar", data.cpuPercent);
  $("memoryValue").textContent = Number(data.memoryPercent || 0).toFixed(1);
  $("memoryDetail").textContent = `${fmtBytes(data.memoryUsedBytes)} / ${fmtBytes(data.memoryTotalBytes)}`;
  setBar("memoryBar", data.memoryPercent);
  $("uptimeValue").textContent = fmtUptime(data.uptimeSeconds);
  setLoad("load1", "load1Bar", data.loadAverage1m, data.logicalCpuCores);
  setLoad("load5", "load5Bar", data.loadAverage5m, data.logicalCpuCores);
  setLoad("load15", "load15Bar", data.loadAverage15m, data.logicalCpuCores);
  const status = safeText(data.overallStatus);
  $("overallStatus").textContent = status;
  $("statusDot").className = `status-dot ${statusClass(status)}`;
  const date = data.collectedAt ? new Date(data.collectedAt) : new Date();
  $("lastUpdated").textContent = `Updated ${date.toLocaleTimeString()}`;
  renderDisks(data.disks);
  $("errorBanner").hidden = true;
}
async function refreshMetrics() {
  const button = $("refreshBtn");
  button.disabled = true;
  try {
    const response = await fetch("/api/health", { cache: "no-store" });
    if (!response.ok) throw new Error(`Backend returned ${response.status}`);
    render(await response.json());
  } catch (error) {
    console.error("Could not retrieve Linux health metrics:", error);
    $("errorBanner").hidden = false;
    $("overallStatus").textContent = "OFFLINE";
    $("statusDot").className = "status-dot critical";
  } finally {
    button.disabled = false;
  }
}
$("refreshBtn").addEventListener("click", refreshMetrics);
refreshMetrics();
setInterval(refreshMetrics, REFRESH_MS);
